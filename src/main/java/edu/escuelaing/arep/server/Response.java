package edu.escuelaing.arep.server;

/**
 * Representation of an HTTP Response in the MicroFramework.
 * In a more complex framework, it could allow setting headers and status codes.
 * For this simplified version, it is provided to match typical routing signatures.
 */
public class Response {
    private String body;
    private int statusCode = 200;
    private String contentType = "text/html";

    public Response() {
        this.body = "";
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
