<#import "../layout.ftl" as layout>
<#import "platform.ftl" as platform>
<@layout.main title="Platforms" page_name="platforms" pagination={"home": "/", "platforms": "/platforms"}>
<h2>Platform pls</h2>
<p>Here are all your current platforms and a button to add another!</p>
<div class="row">
	<#if platforms?has_content>
		<#assign keys = platforms?keys>
		<#if keys?size != -1>
			<#list keys as name>
				<#assign id = platforms[name]>
				<@platform.platform id=id heading=name/>
			</#list>
		</#if>
	</#if>
	<div class="col-md-3">
	    <div class="panel panel-default">
	        <div class="panel-body">
	            <a href="/add_platform">
	                <div class="btn btn-lg btn-block btn-primary">Add a platform</div>
	            </a>
	        </div>
	    </div>
	</div>
</div>
</@layout.main>