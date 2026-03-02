package edu.escuelaing.arep.testapp;

import edu.escuelaing.arep.server.HttpServer;
import static edu.escuelaing.arep.MicroFramework.*;

/**
 * Sample Application showing how developers will use the MicroFramework.
 */
public class Main {
    public static void main(String[] args) {
        // Set the static files location
        staticfiles("/webroot");

        // Define a GET REST Service with query parameter extraction
        get("/hello", (req, res) -> {
            String name = req.getValues("name");
            if (name == null || name.isEmpty()) {
                name = "world";
            }
            return "{\"message\": \"Hello " + name + "!\"}";
        });

        // Define a GET REST Service calculating PI
        get("/pi", (req, res) -> {
            return String.valueOf(Math.PI);
        });

        // Starts the server
        HttpServer.getInstance().start(8080);
    }
}
