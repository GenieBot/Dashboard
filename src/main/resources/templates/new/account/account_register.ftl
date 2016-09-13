<#import "account_layout.ftl" as layout>
<@layout.account
content_title="Register"
content_subtitle="Create a new GenieBot account!"
bottom_tabs={
	"login": "/account/login",
	"register": ["/account/register", true]
}
>
<div class="columns">
	<div class="column is-half">
        <div class="box">
            <div class="content">
	            <h3 style="text-align: center">
	                Create a new account
                </h3>
            </div>
            <form action="/auth/register" method="post">
                <label class="label">Email</label>
                <p class="control has-icon">
                    <input class="input" type="email" placeholder="Email" name="email">
                    <i class="fa fa-envelope"></i>
                </p>
                <label class="label">Password</label>
                <p class="control has-icon">
                    <input class="input" type="password" placeholder="Password" name="password">
                    <i class="fa fa-lock"></i>
                </p>
                <label class="label">Confirm password</label>
                <p class="control has-icon">
                    <input class="input" type="password" placeholder="Password" name="confirm_password">
                    <i class="fa fa-lock"></i>
                </p>
                <p class="control">
                    <button type="submit" class="button is-success is-fullwidth">
                        Register
                    </button>
                </p>
            </form>
        </div>
	</div>
    <div class="column is-half">
	    <div class="box">
		    <div class="content">
                <h3 style="text-align: center">
                    Already have an account?
                </h3>
			    <a href="/account/register" class="button is-large is-fullwidth">
				    Login
			    </a>
		    </div>
	    </div>
    </div>
</div>
</@layout.account>