<#import "account_layout.ftl" as layout>
<@layout.account
content_title="Overview"
content_subtitle="Here's some information about your account"
bottom_tabs={
	"overview": ["/account", true],
	"change password": "/account/password",
	"logout": "/account/logout"
}
>
meme
</@layout.account>