package io.sponges.bot.dashboard;

import freemarker.template.Configuration;
import io.sponges.bot.dashboard.entity.Account;
import io.sponges.bot.dashboard.entity.ConfigurationLoader;
import io.sponges.bot.dashboard.pages.DonatePage;
import io.sponges.bot.dashboard.pages.IndexPage;
import io.sponges.bot.dashboard.pages.SupportPage;
import io.sponges.bot.dashboard.pages.account.AccountPage;
import io.sponges.bot.dashboard.pages.account.ChangePasswordPage;
import io.sponges.bot.dashboard.pages.auth.*;
import io.sponges.bot.dashboard.pages.discord.DiscordEndpointPage;
import io.sponges.bot.dashboard.pages.networks.NetworksPage;
import io.sponges.bot.dashboard.pages.platform.AddPlatformPage;
import io.sponges.bot.dashboard.pages.platform.ConfirmAddPlatformPage;
import io.sponges.bot.dashboard.pages.platform.DeletePlatformPage;
import io.sponges.bot.dashboard.pages.platform.PlatformsPage;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Routes {

    public static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";

    private final Service service;
    private final TemplateEngine engine;
    private final AccountManager accountManager = new AccountManager();

    @SuppressWarnings("ConstantConditions")
    public Routes(Database database) throws IOException {
        ConfigurationLoader.Configuration configuration = ConfigurationLoader.load(new File("config.json"));

        this.service = Service.ignite();
        this.service.port(4567);
        this.service.staticFileLocation("static");
        this.service.staticFiles.expireTime(600);
        
        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        this.engine = new FreeMarkerEngine(config);

        register(
                // static
                new IndexPage(),
                new SupportPage(),
                new DonatePage(),

                // auth
                new LoginPage(),
                new RegisterPage(),
                new LogoutPage(),
                new ConfirmLoginPage(database, configuration),
                new ConfirmRegisterPage(database, configuration),

                // account
                new AccountPage(),
                new ChangePasswordPage(database),

                // platforms
                new PlatformsPage(database),
                new AddPlatformPage(configuration),
                new ConfirmAddPlatformPage(database),
                new DeletePlatformPage(database),

                // discord
                new DiscordEndpointPage(configuration, database, accountManager),

                // networks
                new NetworksPage(database, accountManager)
        );

        this.service.get("/home", (request, response) -> {
            response.redirect("/");
            return null;
        });
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

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public class AccountManager {

        private final Map<String, List<Account>> accounts = new HashMap<>();

        public Map<String, List<Account>> getAccounts() {
            return accounts;
        }

        public List<Account> getAccounts(String session) {
            System.out.println("Get session id=" + session);
            List<Account> accounts = this.accounts.get(session);
            System.out.println(this.accounts.toString());
            return accounts;
        }

        public boolean add(Account account) {
            String session = account.getSession().id();
            System.out.println("Add session id=" + account.getSession().id());
            List<Account> accounts;
            if (this.accounts.containsKey(session)) {
                accounts = this.accounts.get(session);
            } else {
                accounts = new ArrayList<>();
            }
            if (accounts.contains(account)) return false;
            accounts.add(account);
            this.accounts.put(session, accounts);
            return true;
        }

        public boolean remove(String session) {
            if (!accounts.containsKey(session)) return false;
            accounts.remove(session);
            return true;
        }

        public boolean remove(Account account) {
            String session = account.getSession().id();
            List<Account> accounts;
            if (this.accounts.containsKey(session)) {
                accounts = this.accounts.get(session);
            } else {
                return false;
            }
            if (accounts.contains(account)) return false;
            accounts.remove(account);
            return true;
        }

    }

}
