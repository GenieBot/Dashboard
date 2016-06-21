<#macro main title="Default" page_name="Default">
<!DOCTYPE html>
<html>
<head>
    <title>${title} | The Genie</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<div id="internal_data">${internal_data}</div>
<div id="current_page">${page_name}</div>
<body>
	<div class="header">THE GENIE</div>
	<div class="content">
        <div class="alert"></div>
		<#nested/>
	</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="internal.js"></script>
</body>
</html>
</#macro>