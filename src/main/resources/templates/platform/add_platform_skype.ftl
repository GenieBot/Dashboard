<h3>How to do it:</h3>
<ol>
    <li>Send "-addplatform &lt;email&gt; &lt;request token&gt;" to the genie on the desired platform, e.g. skype</li>
    <li>The genie will then send your id and secret token. DO NOT SHARE THE SECRET TOKEN!</li>
    <li>In the form below select the platform, put your user id in the user id box</li>
    <li>and your secret token in the user secret box</li>
    <li>Then press submit</li>
</ol>
<h3>Command parameters</h3>
<p>Your email: <code>${user_email}</code></p>
<p>Your request token (DO NOT SHARE): <code>dummy_token</code></p>
<p>Command: <code>-addplatform ${user_email} dummy_token</code></p>
<h3>The form</h3>
<form action="/confirm_add_platform" method="post">
    Platform:
    <select name="platform">
        <option value="skype">Skype</option>
        <option value="discord">Discord</option>
    </select>
    <br />
    User id: <input type="text" name="id">
    <br />
    User secret: <input type="text" name="secret">
    <br />
    <button type="submit">Submit</button>
</form>