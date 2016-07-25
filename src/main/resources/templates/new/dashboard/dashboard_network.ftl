<#import "dashboard_layout.ftl" as layout>
<@layout.dashboard
content_title="SpongyBot Chat"
content_subtitle="Manage your network here!"
content_image="https://im.not.ovh/eZcmSziL5m2l63R.jpeg"
top_tabs={
	"Skype": "/platforms/skype",
	"Discord": ["/platforms/discord", true],
	"Slack": "/platforms/slack",
	"Telegram": "/platforms/telegram",
"+": "/platforms/manage"
}
bottom_tabs={
	"SpongyBot Chat": ["/networks/t6gy8i23ng", true],
	"End me: 17": "/networks/ajhu32t2r9p84",
	"Yive's Chat": "/networks/azxsed5f5rty89u",
	"h": "/networks/o98mi7ju654w",
	"Dev Ch@t": "/networks/zsxdcfv698o",
	"Meme crew 11": "/networks/3ftg893hu2jbfn",
	"+": "/networks/add"
}
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
<#assign sidebar_url_prefix = "/networks/${network}/">
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