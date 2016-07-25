<#import "account_layout.ftl" as layout>
<@layout.account
content_title="Change password"
content_subtitle="Here you can change the password used to login to your account"
bottom_tabs={
	"overview": "/account",
	"change password": ["/account/password", true],
	"logout": "/account/logout"
}
>
<form action="/auth/login" method="post">
    <label class="label">Old password</label>
    <p class="control has-icon">
        <input class="input" type="password" placeholder="Old password">
        <i class="fa fa-lock"></i>
    </p>
    <label class="label">New password</label>
    <p class="control has-icon">
        <input class="input" type="password" placeholder="New password">
        <i class="fa fa-lock"></i>
    </p>
    <label class="label">Confirm new password</label>
    <p class="control has-icon">
        <input class="input" type="password" placeholder="New password">
        <i class="fa fa-lock"></i>
    </p>
    <p class="control">
        <button type="submit" class="button is-success is-fullwidth">
            Change password
        </button>
    </p>
</form>
</@layout.account>