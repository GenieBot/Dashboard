<#assign keys = pagination?keys>
<#if !keys?seq_contains("none")>
	<ol class="breadcrumb">
		<#list keys as name>
			<#if !name_has_next>
		        <li class="active">${name}</li>
			<#else>
		        <li><a href="${pagination[name]}">${name}</a></li>
			</#if>
		</#list>
	</ol>
</#if>