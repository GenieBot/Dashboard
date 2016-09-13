<#import "dashboard_layout.ftl" as layout>
<@layout.dashboard
content_title=network_name
content_subtitle="Manage your network here!"
top_tabs=top_tabs
bottom_tabs=bottom_tabs
>
<#assign sidebar = {
"general": {
	"overview": ["overview", true],
	"settings": "settings",
	"chat": "chat",
	"members": "members",
	"statistics": "statistics"
},
"moderation": {
	"moderation log": "moderation/log",
	"spam protection": "moderation/spam"
},
"music": {
	"settings": "music/settings",
	"song queue": "music/queue",
	"web access": "music/web"
},
"chat bridges": {
	"settings": "bridges/settings",
	"manage bridges": "bridges/manage"
}
}>
<#assign sidebar_url_prefix = "/platforms/${platform}/networks/${network}/">
<div class="columns">
	<div class="column is-one-quarter">
		<#include "dashboard_network_sidebar.ftl">
	</div>
	<div class="column">
        <h1 class="title">Title</h1>
        <h2 class="subtitle">Subtitle</h2>
	</div>
</div>
</@layout.dashboard>