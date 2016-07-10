<#macro module enabled=true>
<div class="col-md-3">
	<#if enabled>
	    <#assign panel = "default" />
	<#else>
		<#assign panel = "danger" />
	</#if>
    <div class="panel panel-${panel}">
        <div class="panel-heading">
            A module
	        <#if !enabled>
                <span class="label label-danger pull-right">Disabled</span>
	        </#if>
        </div>
        <div class="panel-body">
            <p>This is a description<br />for a module!<br />Woah such a damn long description dude.</p>
        </div>
        <div class="panel-footer">
            <a href="/manage_module?platform=${platform_id}&network=${network_id}&module=dummymoduleid">
                <div class="btn btn-default btn-block btn-sm">
                    Manage Module
                </div>
            </a>
        </div>
    </div>
</div>
</#macro>