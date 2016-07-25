<#macro basic
logged_in=true
navigation=false
title="Default"
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.1.0/css/bulma.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body class="layout-documentation page-overview">
	<section class="hero is-info is-bold">
		<#include "navigation_top.ftl">
		<#include "header.ftl">
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
                        Warning lorem ipsum dolor sit amet, consectetur
                        adipiscing elit lorem ipsum dolor sit amet,
                        consectetur adipiscing elit
                    </div>
                </div>
                <br />
            </div>
			<#nested />
        </div>
	</section>
	<section id="newsletter" class="hero is-primary is-bold">
	    <div class="hero-body">
	        <div class="container">
	            <div class="columns is-vcentered">
	                <div class="column is-one-third is-left">
	                    <p class="title">
		                    The <strong>Store</strong> <span class="tag is-success">50% OFF!</span>
	                    </p>
	                    <p class="subtitle">Check out our awesome packages!</p>
	                </div>
	                <div class="column">
		                <div class="button is-white is-outlined"></div>
	                </div>
	            </div>
	        </div>
	    </div>
	</section>
	<footer class="footer">
	    <div class="container">
	        <div class="content has-text-centered">
	            <p>
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