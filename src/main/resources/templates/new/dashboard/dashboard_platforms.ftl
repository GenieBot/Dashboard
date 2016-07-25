<#import "dashboard_layout.ftl" as layout>
<@layout.dashboard
content_title="Platforms"
content_subtitle="Here you can manage where the Genie is available!"
bottom_tabs={
"Skype": "/platforms/skype",
"Discord": "/platforms/discord",
"Slack": "/platforms/slack",
"Telegram": "/platforms/telegram",
"+": "/platforms/manage"
}
>
</@layout.dashboard>