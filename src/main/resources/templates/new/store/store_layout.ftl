<#macro store
content_heading=true
content_title="Default title"
content_subtitle="Default subtitle"
bottom_tabs={}
>
<#import "../layout/main_layout.ftl" as layout>
<@layout.main
title=content_title
header_title="Store"
header_subtitle="Buy lamps from the genie!"
header_link="/store"
content_heading=content_heading
content_title=content_title
content_subtitle=content_subtitle
bottom_tabs=bottom_tabs
>
<#nested />
</@layout.main>
</#macro>