package io.sponges.bot.dashboard.newdesign;

import freemarker.template.Configuration;
import io.sponges.bot.dashboard.newdesign.pages.IndexPage;
import io.sponges.bot.dashboard.newdesign.pages.account.*;
import io.sponges.bot.dashboard.newdesign.pages.auth.AuthChangePasswordPage;
import io.sponges.bot.dashboard.newdesign.pages.auth.AuthLoginPage;
import io.sponges.bot.dashboard.newdesign.pages.auth.AuthLogoutPage;
import io.sponges.bot.dashboard.newdesign.pages.auth.AuthRegisterPage;
import io.sponges.bot.dashboard.newdesign.pages.dashboard.DashboardNetworkPage;
import io.sponges.bot.dashboard.newdesign.pages.dashboard.DashboardPlatformPage;
import io.sponges.bot.dashboard.newdesign.pages.dashboard.DashboardPlatformsPage;
import io.sponges.bot.dashboard.newdesign.pages.store.StoreFaqPage;
import io.sponges.bot.dashboard.newdesign.pages.store.StoreIndexPage;
import io.sponges.bot.dashboard.newdesign.pages.LegalPage;
import io.sponges.bot.dashboard.newdesign.pages.store.StoreSupportPage;
import io.sponges.bot.dashboard.newdesign.platform.account.discord.DiscordAccountFactory;
import io.sponges.bot.dashboard.newdesign.user.UserManager;
import io.sponges.bot.dashboard.newdesign.user.impl.UserManagerImpl;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Routes {

    public static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";

    private final Service service;
    private final TemplateEngine engine;
    private final UserManager userManager;

    @SuppressWarnings("ConstantConditions")
    public Routes(Database database) throws IOException {
        ConfigurationLoader.Configuration configuration = ConfigurationLoader.load(new File("config.json"));

        this.service = Service.ignite();
        this.service.port(4567);
        this.service.staticFileLocation("static");
        this.service.staticFiles.expireTime(600);
        this.service.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });
        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        this.engine = new FreeMarkerEngine(config);

        this.userManager = new UserManagerImpl(database);

        // registering account factories - added to static map when initialised
        new DiscordAccountFactory(database, configuration);

        // registering routes
        register(
                new IndexPage(),

                // account
                new AccountLoginPage(),
                new AccountRegisterPage(),
                new AccountLogoutPage(),
                new AccountOverviewPage(),
                new AccountChangePasswordPage(),

                // auth
                new AuthLoginPage(database, configuration),
                new AuthRegisterPage(database, configuration),
                new AuthLogoutPage(userManager),
                new AuthChangePasswordPage(database),

                // store
                new StoreIndexPage(),
                new StoreSupportPage(),
                new StoreFaqPage(),
                new LegalPage(),

                // dashboard
                new DashboardPlatformsPage(),
                new DashboardPlatformPage(),
                new DashboardNetworkPage()
        );

        this.service.redirect.get("/login", "/account/login");
        this.service.redirect.get("/dashboard", "/platforms");
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
                                page.internalExecute(userManager, request, response, Model.create()), engine);
            } else {
                clazz.getMethod(methodName, String.class, Route.class).invoke(service,
                        page.getRoute(), (Route) (request, response) ->
                                page.internalExecute(userManager, request, response, Model.create()));
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
