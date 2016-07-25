<aside class="menu">
	<#assign keys = sidebar?keys>
	<#list keys as name>
		<p class="menu-label">
			${name?capitalize}
		</p>
		<#assign items = sidebar[name]>
		<#assign itemKeys = items?keys>
		<ul class="menu-list">
			<#list itemKeys as itemName>
				<#assign item = items[itemName]>
				<#assign active = false>
				<#if item?is_enumerable>
					<#assign url = item[0]>
					<#if item[1]??>
						<#assign active = item[1]>
					</#if>
				<#else>
					<#assign url = item>
				</#if>
		        <li>
			        <#assign link = sidebar_url_prefix + url>
			        <a <#if active>class="is-active"</#if> href="${link}">
			            ${itemName?capitalize}
			        </a>
		        </li>
			</#list>
		</ul>
	</#list>
</aside>