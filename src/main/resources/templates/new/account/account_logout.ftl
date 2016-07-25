<#import "account_layout.ftl" as layout>
<@layout.account
content_heading=false
content_title="Logout"
bottom_tabs={
	"overview": "/account",
	"change password": "/account/password",
	"logout": ["/account/logout", true]
}
>
<div style="text-align: center">
    <h1 class="title">Are you sure you wish to logout?</h1>
    <h2 class="subtitle">You will have to login again to access the dashboard.</h2>
    <hr />
</div>
<div class="columns">
	<div class="column is-half is-offset-3" style="text-align: center">
        <form action="/auth/logout" method="post">
            <button type="submit" class="button is-danger is-large">
                Logout
            </button>
            <a href="/account" class="button is-large">
                Cancel
            </a>
        </form>
	</div>
</div>
</@layout.account>