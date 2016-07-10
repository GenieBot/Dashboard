package io.sponges.bot.dashboard.pages.networks;

import io.sponges.bot.dashboard.Model;
import io.sponges.bot.dashboard.Page;
import io.sponges.bot.dashboard.Routes;
import io.sponges.bot.dashboard.entity.Account;
import io.sponges.bot.dashboard.network.Network;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.List;

public class ManageModulePage extends Page {

    private final Routes.AccountManager accountManager;

    public ManageModulePage(Routes.AccountManager accountManager) {
        super("/manage_module", Method.GET, true, true);
        this.accountManager = accountManager;
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        Session session = request.session();
        if (request.queryParams().size() < 3) {
            session.attribute("alert", "Invalid query paramaters!");
            response.redirect("/platforms");
            return null;
        }
        if (!request.queryParams().contains("platform")) {
            session.attribute("alert", "Invalid platform id!");
            response.redirect("/platforms");
            return null;
        }
        String platformId = request.queryParams("platform");
        if (!request.queryParams().contains("network")) {
            session.attribute("alert", "Invalid network id!");
            response.redirect("/networks?platform=" + platformId);
            return null;
        }
        String networkId = request.queryParams("network");
        if (!request.queryParams().contains("module")) {
            session.attribute("alert", "Invalid module id!");
            response.redirect("/manage_network?platform=" + platformId + "&network=" + networkId);
            return null;
        }
        String moduleId = request.queryParams("module");
        List<Account> accounts = accountManager.getAccounts(session.id());
        Account account = null;
        for (Account acc : accounts) {
            if (acc.getPlatform().equalsIgnoreCase(platformId)) {
                account = acc;
                break;
            }
        }
        if (account == null) {
            session.attribute("alert", "No account for that platform?");
            response.redirect("/platforms");
            return null;
        }
        Network network = account.getNetworkCache().get(networkId);
        if (network == null) {
            session.attribute("alert", "Invalid network \"" + networkId + "\"!");
            response.redirect("/networks?platform=" + platformId);
            return null;
        }
        builder.with("platform_id", platformId);
        builder.with("network_id", network.getId());
        builder.with("network_name", network.getName());
        builder.with("module_id", moduleId);
        builder.with("module_name", "Dummy Module Name");
        return new ModelAndView(builder.build(), "network/manage_module.ftl");
    }
}
