package edu.escuelaing.arep;

import edu.escuelaing.arep.server.HttpServer;
import edu.escuelaing.arep.server.Route;

/**
 * The main API for developers to define REST services and static file directories.
 */
public class MicroFramework {

    /**
     * Defines a REST route for a GET request.
     * @param path the URL path to map.
     * @param route the lambda handler for the logic.
     */
    public static void get(String path, Route route) {
        HttpServer.getInstance().get(path, route);
    }

    /**
     * Specifies the location of static files.
     * @param dir the directory name (e.g. "/webroot")
     */
    public static void staticfiles(String dir) {
        HttpServer.getInstance().staticfiles(dir);
    }
}

