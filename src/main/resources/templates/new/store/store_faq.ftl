<#import "store_layout.ftl" as layout>
<@layout.store
content_title="Frequently Asked Questions"
content_subtitle="Things you keep asking that can't be bothered to answer!"
bottom_tabs={
	"store": "/store",
	"billing support": "/store/support",
	"frequently asked questions": ["/store/faq", true],
	"legal information": "/legal"
}
>
<div class="columns">
	<@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
    <@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
    <@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
</div>
<div class="columns">
	<@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
    <@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
    <@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
</div>
<div class="columns">
	<@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
    <@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
    <@faq "Do you like memes?" "No, I really fucking dislike memes you asshole. I cannot fucking believe you would ask that."/>
</div>
</@layout.store>

<#macro faq q="Default question" a="Default answer">
	<div class="column is-one-third">
	    <div class="content">
	        <h4>${q}</h4>
	        <p>${a}</p>
	    </div>
	</div>
</#macro>