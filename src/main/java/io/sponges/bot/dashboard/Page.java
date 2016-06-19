package io.sponges.bot.dashboard;

import spark.Request;
import spark.Response;

public abstract class Page {

    private final String route;
    private final Method method;
    private final boolean template;

    public Page(String route, Method method, boolean template) {
        this.route = route;
        this.method = method;
        this.template = template;
    }

    public abstract Object execute(Request request, Response response, Model.Builder builder);

    public String getRoute() {
        return route;
    }

    public Method getMethod() {
        return method;
    }

    public boolean isTemplate() {
        return template;
    }

    public enum Method {
        GET, POST, PUT, PATCH, DELETE, OPTIONS
    }
}
