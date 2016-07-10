<#import "../layout.ftl" as layout>
<#import "module.ftl" as module>
<@layout.main title="Networks" page_name="networks" pagination={"home": "/", "platforms": "/platforms", "${platform_id} networks": "/networks?platform=${platform_id}", "${network_name}": "/manage_network?platform=${platform_id}&network=${network_id}"}>
<h2>Manage zat network dude!</h2>
<p>So basically u can manage ze network.</p>
<br />
<h3>Modules</h3>
<p>So dese ze modules boi!</p>
<br />
<div class="row">
	<@module.module />
	<@module.module />
	<@module.module false />
	<@module.module />
	<@module.module />
	<@module.module false />
	<@module.module />
</div>
</@layout.main>