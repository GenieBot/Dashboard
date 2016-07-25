<#import "dashboard_layout.ftl" as layout>
<@layout.dashboard
content_title=platform?capitalize
content_subtitle="Here you can manage all your networks!"
top_tabs={
	"Skype": "/platforms/skype",
	"Discord": ["/platforms/discord", true],
	"Slack": "/platforms/slack",
	"Telegram": "/platforms/telegram",
	"+": "/platforms/manage"
}
bottom_tabs={
	"SpongyBot Chat": "/networks/t6gy8i23ng",
	"End me: 17": "/networks/ajhu32t2r9p84",
	"Yive's Chat": "/networks/azxsed5f5rty89u",
	"h": "/networks/o98mi7ju654w",
	"Dev Ch@t": "/networks/zsxdcfv698o",
	"Meme crew 11": "/networks/3ftg893hu2jbfn",
	"+": "/networks/add"
}
>
ye boi
</@layout.dashboard>