<#import "../layout.ftl" as layout>
<@layout.main title="Register" page_name="register" pagination={"home": "/", "register": "/register"} captcha=true>
<p>Register pls</p>
<form action="/confirm_register" method="post">
	Email: <input type="text" name="email"><br>
	Password: <input type="password" name="password"><br>
    Confirm password: <input type="password" name="confirm_password"><br>
    <div class="g-recaptcha" data-sitekey="6LcrESQTAAAAAKjka65Dcd8zSUjvlqmt6G0mO_TH"></div>
	<button type="submit">Submit</button>
</form>
</@layout.main>