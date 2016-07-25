<#import "account_layout.ftl" as layout>
<@layout.account
content_title="Login"
content_subtitle="Please enter your GenieBot account credentials!"
bottom_tabs={
	"login": ["/account/login", true],
	"register": "/account/register"
}
>
<div class="columns">
	<div class="column is-half">
        <div class="box">
            <div class="content">
                <h3 style="text-align: center">
                    Login to your account
                </h3>
            </div>
            <form action="/auth/login" method="post">
                <label class="label">Email</label>
                <p class="control has-icon">
                    <input class="input" type="email" placeholder="Email">
                    <i class="fa fa-envelope"></i>
                </p>
                <label class="label">Password</label>
                <p class="control has-icon">
                    <input class="input" type="password" placeholder="Password">
                    <i class="fa fa-lock"></i>
                </p>
                <p class="control">
                    <button type="submit" class="button is-success is-fullwidth">
                        Login
                    </button>
                </p>
            </form>
        </div>
	</div>
    <div class="column is-half">
	    <div class="box">
		    <div class="content">
                <h3 style="text-align: center">
                    Don't have an account?
                </h3>
			    <a href="/account/register" class="button is-large is-fullwidth">
				    Register
			    </a>
		    </div>
	    </div>
    </div>
</div>
</@layout.account>