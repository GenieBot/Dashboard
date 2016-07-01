<#import "../layout.ftl" as layout>
<@layout.main title="Account" page_name="account" pagination={"home": "/", "account": "/account"}>
<h2>Account pls</h2>
<p>Your id: ${user_id}</p>
<p>Your email: ${user_email}</p>
<br />
<h3>Change password</h3>
<form action="/change_password" method="post">
    Current password: <input type="password" name="current"><br>
    New password: <input type="password" name="new"><br>
    Confirm password: <input type="password" name="confirm"><br>
    <button type="submit">Submit</button>
</form>
</@layout.main>