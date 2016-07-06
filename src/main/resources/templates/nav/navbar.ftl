<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li id="home_tab"><a href="/">Home</a></li>
                <li id="support_tab"><a href="/support">Support</a></li>
                <li id="donate_tab"><a href="/donate">Donate</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
	            <#if logged_in>
                    <li id="platforms_tab"><a href="/platforms">Platforms</a></li>
		            <#include "account_tab.ftl">
	            <#else>
		            <#include "login_tab.ftl">
	            </#if>
            </ul>
        </div>
    </div>
</nav>