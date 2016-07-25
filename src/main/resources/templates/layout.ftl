<#macro main title="Default" page_name="Default" pagination={"none": ""} captcha=false advertisements=false>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- meta shit -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- content shit -->
    <title>${title} | The Genie</title>

	<!-- style shit -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
	<!-- i'm so fucking lazy -->
	<link rel="stylesheet" type="text/css" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">

	<#if advertisements>
		<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
		<script>
			(adsbygoogle = window.adsbygoogle || []).push({
			google_ad_client: "ca-pub-6568927978510442",
			enable_page_level_ads: true
			});
		</script>
	</#if>

	<#if captcha>
        <!-- recaptcha shit -->
        <script src='https://www.google.com/recaptcha/api.js'></script>
	</#if>
</head>
<body>
	<!-- internal shit -->
	<div id="internal_data">${internal_data}</div>
	<div id="current_page">${page_name}</div>

	<!-- content shit -->
	<div class="container">
		<!-- alert -->
		<#include "alert.ftl">

		<!-- header -->
        <div class="header">
            The Genie
        </div>

		<!-- navbar & pagination -->
		<#include "nav/navbar.ftl">
		<#include "pagination.ftl">

		<!-- start of actual content -->
		<#nested />
		<!-- end of actual content -->

		<!-- advertisement -->
		<#if advertisements>
            <hr />
            <center>
				<ins class="adsbygoogle"
	                 style="display:block"
	                 data-ad-client="ca-pub-6568927978510442"
	                 data-ad-slot="3998604482"
	                 data-ad-format="auto">
	            </ins>
	            <script>(adsbygoogle = window.adsbygoogle || []).push({});</script>
				<br />
				<span style="color: #5c5c5c">
					Don't like advertisements? <a href="/donate">Donors</a> get an ad-free experience!
				</span>
            </center>
		</#if>

		<!-- footer -->
		<hr />
		<p style="text-align: center;">Copyright &copy; ${.now?string.yyyy} sponges.io</p>
	</div>

	<!-- script shit -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/internal.js"></script>
</body>
</html>
</#macro>