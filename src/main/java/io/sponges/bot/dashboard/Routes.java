package io.sponges.bot.dashboard;

import freemarker.template.Configuration;
import io.sponges.bot.dashboard.pages.IndexPage;
import io.sponges.bot.dashboard.pages.auth.*;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Routes {

    private final Service service;
    private final TemplateEngine engine;

    public Routes(Database database) throws IOException {
        this.service = Service.ignite();
        this.service.port(4567);
        this.service.staticFileLocation("static");
        this.service.staticFiles.expireTime(600);
        
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        this.engine = new FreeMarkerEngine(configuration);

        register(
                new IndexPage(),

                // auth
                new LoginPage(),
                new RegisterPage(),
                new LogoutPage(),
                new ConfirmLoginPage(database),
                new ConfirmRegisterPage(database)
        );
    }

    private void register(Page... pages) {
        for (Page page : pages) {
            register(page);
        }
    }

    public void register(Page page) {
        Class<? extends Service> clazz = service.getClass();
        String methodName = page.getMethod().name().toLowerCase();
        try {
            if (page.isTemplate()) {
                clazz.getMethod(methodName, String.class, TemplateViewRoute.class, TemplateEngine.class).invoke(service,
                        page.getRoute(), (TemplateViewRoute) (request, response) -> (ModelAndView)
                                page.internalExecute(request, response, Model.create()), engine);
            } else {
                clazz.getMethod(methodName, String.class, Route.class).invoke(service,
                        page.getRoute(), (Route) (request, response) ->
                                page.internalExecute(request, response, Model.create()));
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
