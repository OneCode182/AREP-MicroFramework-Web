package edu.escuelaing.arep.server;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

/**
 * The core HTTP Server capable of routing requests to REST lambdas or serving static files.
 */
public class HttpServer {
    private static HttpServer instance;
    private final Map<String, Route> getRoutes = new HashMap<>();
    private String staticFilesDir = null;
    private boolean running = false;
    private ServerSocket serverSocket;

    private HttpServer() { }

    /**
     * Singleton instance of the server.
     * @return the unique instance.
     */
    public static HttpServer getInstance() {
        if (instance == null) {
            instance = new HttpServer();
        }
        return instance;
    }

    /**
     * Registers a GET route.
     * @param path The path of the endpoint.
     * @param route The lambda expression to execute.
     */
    public void get(String path, Route route) {
        getRoutes.put(path, route);
    }

    /**
     * Sets the directory for static files.
     * @param dir the directory path.
     */
    public void staticfiles(String dir) {
        this.staticFilesDir = dir;
    }

    /**
     * Starts the server on the specified port.
     * @param port the port to listen on.
     */
    public void start(int port) {
        running = true;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Ready to receive connections on port " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
        }
    }
    
    public void stop() {
        running = false;
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true)
        ) {
            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) return;

            String[] reqParts = requestLine.split(" ");
            if (reqParts.length < 2) return;

            String method = reqParts[0];
            String uri = reqParts[1];
            
            // Read remaining headers (optional, skipping for simplicity)
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (!in.ready() || inputLine.isEmpty()) break;
            }

            Request req = new Request(uri);
            Response res = new Response();

            if (method.equals("GET")) {
                if (getRoutes.containsKey(req.getPath())) {
                    // Handle REST Route
                    Route route = getRoutes.get(req.getPath());
                    String body = route.handle(req, res);
                    
                    sendResponse(writer, 200, res.getContentType(), body.getBytes());
                } else {
                    // Try static files
                    if (staticFilesDir != null) {
                        serveStaticFile(req.getPath(), out, writer);
                    } else {
                        sendResponse(writer, 404, "text/plain", "Not Found".getBytes());
                    }
                }
            } else {
                sendResponse(writer, 405, "text/plain", "Method Not Allowed".getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serveStaticFile(String path, OutputStream out, PrintWriter writer) throws IOException {
        String defaultFile = path.equals("/") ? "/index.html" : path;
        String filePath = "target/classes" + staticFilesDir + defaultFile;
        // Allows testing without full mvn install by also looking at src/main/resources
        if(!new File(filePath).exists()) {
             filePath = "src/main/resources" + staticFilesDir + defaultFile;
        }

        File file = new File(filePath);

        if (file.exists() && !file.isDirectory()) {
            String contentType = getContentType(filePath);
            byte[] fileData = Files.readAllBytes(file.toPath());
            
            writer.print("HTTP/1.1 200 OK\r\n");
            writer.print("Content-Type: " + contentType + "\r\n");
            writer.print("Content-Length: " + fileData.length + "\r\n");
            writer.print("\r\n");
            writer.flush();
            out.write(fileData, 0, fileData.length);
            out.flush();
        } else {
            sendResponse(writer, 404, "text/plain", "File Not Found".getBytes());
        }
    }

    private String getContentType(String path) {
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        return "text/plain";
    }

    private void sendResponse(PrintWriter writer, int statusCode, String contentType, byte[] body) {
        writer.print("HTTP/1.1 " + statusCode + " OK\r\n");
        writer.print("Content-Type: " + contentType + "\r\n");
        writer.print("Content-Length: " + body.length + "\r\n");
        writer.print("\r\n");
        writer.flush();
        if (body.length > 0) {
            try {
                // If the response is text, print writer is fine, but for proper byte writing:
                String bodyStr = new String(body);
                writer.print(bodyStr);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
