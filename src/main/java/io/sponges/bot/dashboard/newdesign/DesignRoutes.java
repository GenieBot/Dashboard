package io.sponges.bot.dashboard.newdesign;

import freemarker.template.Configuration;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DesignRoutes {

    public static void main(String[] args) throws IOException {
        Service service = Service.ignite();
        service.port(4567);
        service.staticFileLocation("static");
        service.staticFiles.expireTime(600);

        service.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });

        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        TemplateEngine engine = new FreeMarkerEngine(config);

        final Map emptyMap = new HashMap<>();

        service.get("/", (request, response) -> new ModelAndView(emptyMap, "new/index.ftl"), engine);

        service.redirect.get("/dashboard", "/platforms");
        service.get("/platforms", (request, response) -> new ModelAndView(emptyMap, "new/dashboard/dashboard_platforms.ftl"), engine);
        service.get("/platforms/manage", (request, response) -> new ModelAndView(emptyMap, "new/dashboard/dashboard_manage_platforms.ftl"), engine);
        service.get("/platforms/:platform", (request, response) -> new ModelAndView(new HashMap<String, Object>() {{
            put("platform", request.params("platform"));
        }}, "new/dashboard/dashboard_platform.ftl"), engine);
        service.get("/networks/:network", (request, response) -> new ModelAndView(new HashMap<String, Object>() {{
            put("network", request.params("network"));
        }}, "new/dashboard/dashboard_network.ftl"), engine);

        service.get("/store", (request, response) -> new ModelAndView(emptyMap, "new/store/store_index.ftl"), engine);
        service.get("/store/support", (request, response) -> new ModelAndView(emptyMap, "new/store/store_support.ftl"), engine);
        service.get("/store/faq", (request, response) -> new ModelAndView(emptyMap, "new/store/store_faq.ftl"), engine);

        service.get("/account", (request, response) -> new ModelAndView(emptyMap, "new/account/account_overview.ftl"), engine);
        service.redirect.get("/login", "/account/login");
        service.redirect.get("/logout", "/account/logout");
        service.redirect.get("/register", "/account/register");
        service.get("/account/login", (request, response) -> new ModelAndView(emptyMap, "new/account/account_login.ftl"), engine);
        service.get("/account/logout", (request, response) -> new ModelAndView(emptyMap, "new/account/account_logout.ftl"), engine);
        service.get("/account/register", (request, response) -> new ModelAndView(emptyMap, "new/account/account_register.ftl"), engine);
        service.get("/account/password", (request, response) -> new ModelAndView(emptyMap, "new/account/account_change_password.ftl"), engine);

        service.get("/legal", (request, response) -> new ModelAndView(emptyMap, "new/legal.ftl"), engine);

        service.get("*", (request, response) -> new ModelAndView(new HashMap<String, Object>() {{
            put("status_code", 404);
            put("status_message", "Page not found");
        }}, "new/error.ftl"), engine);
    }

}
