package io.sponges.bot.dashboard;

import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public abstract class Page {

    protected static final Map<Integer, String> SESSIONS = new HashMap<>();

    private final String route;
    private final Method method;
    private final boolean template;
    private final boolean auth;

    public Page(String route, Method method, boolean template, boolean auth) {
        this.route = route;
        this.method = method;
        this.template = template;
        this.auth = auth;
    }

    public Object internalExecute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        String alertKey = "alert";
        if (session.attributes().contains(alertKey)) {
            String alert = session.attribute(alertKey);
            builder.data(alertKey, alert);
            session.removeAttribute(alertKey);
        }
        if (auth && !isAuthorised(session)) {
            session.attribute(alertKey, "You must be logged in to do that!");
            response.redirect("/login");
            return null;
        }
        return execute(request, response, builder);
    }

    protected abstract Object execute(Request request, Response response, Model.Builder builder);

    protected boolean isAuthorised(Session session) {
        if (!session.attributes().contains("id") || !session.attributes().contains("email") || !session.attributes().contains("token")) {
            return false;
        }
        int id = session.attribute("id");
        String token = session.attribute("token");
        return SESSIONS.containsKey(id) && SESSIONS.get(id).equals(token);
    }

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
