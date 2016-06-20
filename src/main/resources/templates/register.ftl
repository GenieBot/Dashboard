<#import "layout.ftl" as layout>
<@layout.main "Register" "register">
<p>Register pls</p>
<form action="/confirm_register" method="post">
	Email: <input type="text" name="email"><br>
	Password: <input type="password" name="password"><br>
    Confirm password: <input type="password" name="confirm_password"><br>
	<button type="submit">Submit</button>
</form>
</@layout.main>