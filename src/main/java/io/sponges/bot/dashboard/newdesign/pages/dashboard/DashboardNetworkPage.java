package io.sponges.bot.dashboard.newdesign.pages.dashboard;

import io.sponges.bot.dashboard.newdesign.Model;
import io.sponges.bot.dashboard.newdesign.Page;
import io.sponges.bot.dashboard.newdesign.platform.account.Account;
import io.sponges.bot.dashboard.newdesign.platform.network.Network;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardNetworkPage extends Page {

    public DashboardNetworkPage() {
        super("/platforms/:platform/networks/:network", Method.GET, true, true);
    }

    @Override
    protected Object execute(Request request, Response response, Model.Builder builder) {
        String platform = request.params("platform");
        String networkId = request.params("network");
        builder.with("platform", platform);
        builder.with("network", networkId);
        Collection<Account> accounts = getUser().getOrLoadAccounts();
        Map<String, Object> topTabs = new LinkedHashMap<>();
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                String p = account.getPlatform();
                String url = "/platforms/" + p;
                Object value;
                if (p.equalsIgnoreCase(platform)) {
                    value = new Object[]{url, true};
                } else {
                    value = url;
                }
                topTabs.put(String.valueOf(p.charAt(0)).toUpperCase() + p.substring(1), value);
            }
        }
        topTabs.put("Manage Platforms", "/platforms/manage");
        builder.with("top_tabs", topTabs);
        Map<String, Object> bottomTabs = new LinkedHashMap<>();
        Account account = getUser().getAccount(platform);
        Network[] networks = account.getNetworks();
        for (Network network : networks) {
            String url = "/platforms/" + platform + "/networks/" + network.getId();
            Object value;
            if (network.getId().equalsIgnoreCase(networkId)) {
                value = new Object[] { url, true };
            } else {
                value = url;
            }
            bottomTabs.put(network.getName(), value);
        }
        bottomTabs.put("Manage Networks", "/platforms/" + platform + "/networks/manage");
        builder.with("bottom_tabs", bottomTabs);
        Network network = account.getNetworkCache().get(networkId);
        builder.with("network_name", network.getName());
        return new ModelAndView(builder.build(), "new/dashboard/dashboard_network.ftl");
    }
}
