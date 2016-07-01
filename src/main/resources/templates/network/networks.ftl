<#import "../layout.ftl" as layout>
<#import "network_row.ftl" as network_row>
<#assign current = "/networks?platform=${platform_id}">
<@layout.main title="Platforms" page_name="platforms" pagination={"home": "/", "platforms": "/platforms", "networks": current}>
<h2>Networks pls</h2>
<p>platform id: ${platform_id}</p>
<p>platform user: ${platform_user}</p>
<table class="table table-bordered table-responsive">
	<tr>
		<th>Name</th>
        <th>ID</th>
        <th>Actions</th>
	</tr>
    <@network_row.network name="SpongyBot Chat | Sponsored by XiPE | http://bot.sponges.io | Temporarily kicking guests" id="233347c71dfad7169ed36a26eec6708a" />
	<@network_row.network name="Yive's Chat | Rules: https://i.tcpr.ca/VtX68J4 | UK LEAVES EU #BREXIT" id="2802231dea3134a92a63860d0caf4708" />
	<@network_row.network name="Dev Chat || https://i.imgur.com/8isWL5d.png" id="ef260e9aa3c673af240d17a266048036" />
</table>
</@layout.main>