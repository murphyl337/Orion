package com.cengage.apprentice.app.main;

import java.util.Hashtable;

public class OrionRequest {
    private String rawRoute;
    private String route;
    private String method;
    private Hashtable<String, String> queryStringTable;
    
    public String getRawRoute() {
        return rawRoute;
    }
    public void setRawRoute(final String rawRoute) {
        this.rawRoute = rawRoute;
    }
    public String getRoute() {
        return route;
    }
    public void setRoute(final String route) {
        this.route = route;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(final String method) {
        this.method = method;
    }
    public Hashtable<String, String> getQueryStringTable() {
        return queryStringTable;
    }
    public void setQueryStringTable(final Hashtable<String, String> queryStringTable) {
        this.queryStringTable = queryStringTable;
    }

}
