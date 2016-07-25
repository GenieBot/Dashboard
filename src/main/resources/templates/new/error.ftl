<#import "layout/main_layout.ftl" as layout>
<@layout.main
header_title="That's an error!"
header_subtitle="Have you lost your way?"
content_heading=false
content_title=status_code
bottom_tabs={
	"contact support": "/support"
}
>
<div style="text-align: center">
    <h1 class="title">${status_code}</h1>
    <h2 class="subtitle">${status_message}</h2>
    <hr />
    Darn! Some errors starting with a `5` may need attention from the developers.
    Please <a href="/support">contact support</a> if you think this is the case.
</div>
</@layout.main>