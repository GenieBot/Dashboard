<#import "../layout.ftl" as layout>
<#assign current = "/networks?platform=${platform_id}">
<@layout.main title="Networks" page_name="networks" pagination={"home": "/", "platforms": "/platforms", "${platform_id} networks": current}>
<h2>Networks pls</h2>
<p>platform id: ${platform_id}</p>
<p>platform user: ${platform_user}</p>
<table id="networks_table" class="table table-bordered table-responsive">
	<tr>
		<th>Name</th>
        <th>ID</th>
        <th>Actions</th>
	</tr>
</table>
</@layout.main>