<nav class="nav has-shadow">
    <div class="container">
        <div class="nav-left">
	        <#assign keys = bottom_tabs?keys>
	        <#list keys as name>
		        <#assign item = bottom_tabs[name]>
	            <#if item?is_enumerable>
	                <#assign url = item[0]>
	                <#assign active = item[1]>
	            <#else>
	                <#assign url = item>
	                <#assign active = false>
	            </#if>
                <a class="nav-item is-tab <#if active>is-active</#if>" href="${url}">
                    ${name?capitalize}
                </a>
	        </#list>
        </div>
    </div>
</nav>