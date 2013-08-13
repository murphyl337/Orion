package com.cengage.apprentice.app.main;

public class OrionRequest {
    private String rawRoute;
    private String route;
    private String method;
    
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

}
