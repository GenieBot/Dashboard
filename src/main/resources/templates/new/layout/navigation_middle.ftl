<div class="hero-foot">
    <div class="container">
        <nav class="tabs is-boxed">
            <ul>
	            <#assign keys = top_tabs?keys>
	            <#list keys as name>
	                <#assign item = top_tabs[name]>
	                <#if item?is_enumerable>
		                <#assign url = item[0]>
		                <#assign active = item[1]>
	                <#else>
		                <#assign url = item>
		                <#assign active = false>
	                </#if>
                    <li <#if active>class="is-active"</#if>>
                        <a href="${url}">${name?capitalize}</a>
                    </li>
	            </#list>
            </ul>
        </nav>
    </div>
</div>