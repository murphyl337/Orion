package com.cengage.apprentice.app.main;

public class OrionRequest {
    private String route;
    private String method;
    
    public OrionRequest(String method, String route){
        this.method = method;
        this.route = route;
    }

    public String getMethod() {
        return method;
    }

    public String getRoute() {
        return route;
    }

}
