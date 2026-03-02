package edu.escuelaing.arep.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Representation of an HTTP Request in the MicroFramework.
 * Parses the query string to extract attributes.
 */
public class Request {
    private String path;
    private Map<String, String> queryParams;

    public Request(String uri) {
        this.queryParams = new HashMap<>();
        parseUri(uri);
    }

    private void parseUri(String uri) {
        if (uri == null || uri.isEmpty()) return;
        
        String[] parts = uri.split("\\?");
        this.path = parts[0];

        if (parts.length > 1) {
            String query = parts[1];
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] kv = pair.split("=");
                if (kv.length == 2) {
                    this.queryParams.put(kv[0], kv[1]);
                } else if (kv.length == 1) {
                    this.queryParams.put(kv[0], "");
                }
            }
        }
    }

    /**
     * Gets the path of the request.
     * @return the request path without the query string.
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets a query value from the request URL.
     * @param key the name of the parameter.
     * @return the value of the parameter, or null if it doesn't exist.
     */
    public String getValues(String key) {
        return queryParams.get(key);
    }
}
