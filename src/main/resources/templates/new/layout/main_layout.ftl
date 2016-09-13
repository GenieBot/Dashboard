<#macro main
title="Default"
header=true
header_title="Current Page"
header_subtitle="This is some <strong>inspirational</strong> sub-text"
header_link=""
content_heading=true
content_title="Default Title"
content_subtitle="Here, take some <strong>generic</strong> sub-text"
content_image=""
navigation=true
top_tabs={}
bottom_tabs={}
>

<#import "layout.ftl" as layout>
<@layout.basic
navigation=navigation
title=title
header=header
header_title=header_title
header_subtitle=header_subtitle
header_link=header_link
top_tabs=top_tabs
bottom_tabs=bottom_tabs
>

<#if content_heading>
	<#if content_image?has_content>
		<img src="${content_image}" height="100px" width="100px" class="box is-pulled-right" style="padding: 5px; z-index: 5;"/>
	</#if>
	<h1 class="title">${content_title}</h1>
	<h2 class="subtitle">${content_subtitle}</h2>
	<hr>
</#if>
<#nested />

</@layout.basic>
</#macro>