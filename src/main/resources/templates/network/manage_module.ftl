<#import "../layout.ftl" as layout>
<@layout.main title="Manage Module" page_name="manage_module" pagination={"home": "/", "platforms": "/platforms", "${platform_id} networks": "/networks?platform=${platform_id}", "${network_name}": "/manage_network?platform=${platform_id}&network=${network_id}", "${module_name}": "/manage_module?platform=${platform_id}&network=${network_id}&module=${module_id}"}>
<h2>Manage zat module dude!</h2>
</@layout.main>