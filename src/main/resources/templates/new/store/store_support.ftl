<#import "store_layout.ftl" as layout>
<@layout.store
content_title="Billing Support"
content_subtitle="Get help regarding payments to the genie"
bottom_tabs={
	"store": "/store",
	"billing support": ["/store/support", true],
	"frequently asked questions": "/store/faq",
	"legal information": "/store/legal"
}
>
<p>
    Please use this email for <strong>billing support and enquiries only</strong>.
    <br />
    For other support, visit our <a href="/support">support page</a>.
    <br />
    <br />
	Email: <code>billing@geniebot.com</code>
</p>
</@layout.store>