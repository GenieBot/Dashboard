<#import "../layout.ftl" as layout>
<@layout.main title="Login" page_name="login" pagination={"home": "/", "login": "/login"} captcha=true>
<p>Login pls</p>
<p>Don't have an account? <a href="/register">Register</a> a new account.</p>
<br />
<form action="/confirm_login" method="post">
	Email: <input type="text" name="email"><br>
	Password: <input type="password" name="password"><br>
    <div class="g-recaptcha" data-sitekey="6LcrESQTAAAAAKjka65Dcd8zSUjvlqmt6G0mO_TH"></div>
	<button type="submit">Submit</button>
</form>
</@layout.main>