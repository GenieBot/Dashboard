<#import "../layout.ftl" as layout>
<@layout.main title="Add platform" page_name="platforms" pagination={"home": "/", "platforms": "/platforms", "add platform": "/add_platform"}>
<h2>Add platform pls</h2>

<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class="active">
		<a href="#skype" aria-controls="skype" role="tab" data-toggle="tab">Skype</a>
	</li>
    <li role="presentation">
        <a href="#discord" aria-controls="discord" role="tab" data-toggle="tab">Discord</a>
    </li>
</ul>

<div class="tab-content">
	<div role="tabpanel" class="tab-pane active" id="skype">
		<#include "add_platform_skype.ftl">
	</div>
    <div role="tabpanel" class="tab-pane" id="discord">
	    <a href="${discord_authorization_url}">
		    <div class="btn btn-lg btn-primary" style="margin-top: 20px;">Login with Discord</div>
	    </a>
    </div>
</div>
</@layout.main>