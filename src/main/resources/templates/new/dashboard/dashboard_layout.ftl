<#import "../layout/main_layout.ftl" as layout>
<#macro dashboard
content_title="Something"
content_subtitle="Some bullshit <strong>generic</strong> sub-text"
content_image=""
top_tabs={}
bottom_tabs={}
>
<@layout.main
title=content_title
header_title="Dashboard"
header_subtitle="<strong>Welcome</strong> to the dashboard!"
header_link="/dashboard"
content_title=content_title
content_subtitle=content_subtitle
content_image=content_image
top_tabs=top_tabs
bottom_tabs=bottom_tabs
>
<#nested />
</@layout.main>
</#macro>