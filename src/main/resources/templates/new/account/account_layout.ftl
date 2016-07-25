<#import "../layout/main_layout.ftl" as layout>
<#macro account
content_heading=true
content_title="Account"
content_subtitle="Some bullshit <strong>generic</strong> sub-text"
content_image=""
top_tabs={}
bottom_tabs={}
>
<@layout.main
title=content_title
header_title="Account"
header_subtitle="Here you can manage your account"
header_link="/account"
content_heading=content_heading
content_title=content_title
content_subtitle=content_subtitle
content_image=content_image
top_tabs=top_tabs
bottom_tabs=bottom_tabs
>
<#nested />
</@layout.main>
</#macro>