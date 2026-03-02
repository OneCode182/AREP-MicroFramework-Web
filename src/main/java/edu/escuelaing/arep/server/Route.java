package edu.escuelaing.arep.server;

/**
 * Functional interface for defining a REST service route.
 */
@FunctionalInterface
public interface Route {
    /**
     * Handles an HTTP request and generates a string response.
     * @param req The HTTP Request
     * @param res The HTTP Response
     * @return The string content of the response
     */
    String handle(Request req, Response res);
}
