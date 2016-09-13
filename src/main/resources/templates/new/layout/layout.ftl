<#macro basic
navigation=false
title="Default"
header=true
header_title="Current Page"
header_subtitle="This is some <strong>inspirational</strong> sub-text"
header_link=""
top_tabs={}
bottom_tabs={}
>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title} | GenieBot</title>
    <meta name="description" content="boiii u betta DESCRIBE THAT SHIT">
    <link rel="stylesheet" href="/new/bulma.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<div id="internal_data" style="display: none">${internal_data}</div>
<body class="layout-documentation page-overview">
	<section class="hero is-info is-bold">
		<#include "navigation_top.ftl">
		<#if header>
			<#include "header.ftl">
		</#if>
	    <#if navigation>
	        <#include "navigation_middle.ftl">
	    </#if>
	</section>
	<#if navigation>
		<#include "navigation_bottom.ftl">
	</#if>
	<section class="section">
        <div class="container">
            <div id="alert" style="display: none">
                <div class="notification is-warning">
                    <button id="alert-remove" class="delete"></button>
                    <div id="alert-content">
                    </div>
                </div>
                <br />
            </div>
			<#nested />
        </div>
	</section>
	<footer class="footer">
	    <div class="container">
	        <div class="content has-text-centered">
	            <p>
                    <a href="/legal">Legal Information</a> | <a href="/support">Support</a>
		            <br />
	                Copyright &copy; ${.now?string.yyyy} <a href="https://sponges.io">sponges.io</a>
	            </p>
	        </div>
	    </div>
	</footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="/new/visual.js"></script>
    <script src="/new/internal.js"></script>
</body>
</#macro>