package com.cengage.apprentice.app.main;

import java.util.Hashtable;

public final class OrionRequest {
    private String rawRoute;
    private String route;
    private String method;
    private Hashtable<String, String> queries;

    public static class Builder {
        private String method;
        private String rawRoute;
        private String route;
        private Hashtable<String, String> queries;

        public Builder method(final String method) {
            this.method = method;
            return this;
        }

        public Builder rawRoute(final String rawRoute) {
            this.rawRoute = rawRoute;
            return this;
        }

        public Builder route(final String route) {
            this.route = route;
            return this;
        }

        public Builder queries(final Hashtable<String, String> queries) {
            this.queries = queries;
            return this;
        }
        
        public OrionRequest build(){
            return new OrionRequest(this);
        }
    }

    private OrionRequest(final Builder builder) {
        method = builder.method;
        rawRoute = builder.rawRoute;
        route = builder.route;
        queries = builder.queries;
    }

    public String getRawRoute() {
        return rawRoute;
    }

    public String getRoute() {
        return route;
    }

    public String getMethod() {
        return method;
    }

    public Hashtable<String, String> getQueries() {
        return queries;
    }

}
