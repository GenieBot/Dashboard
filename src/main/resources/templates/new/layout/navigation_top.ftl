<div class="hero-head">
	<div class="container">
		<nav class="nav">
			<div class="nav-left">
				<a class="nav-item is-active" href="/">
					<span style="font-size: 23px; letter-spacing: 4px; font-weight: bold">GENIE BOT</span>
				</a>
			</div>
			<span id="nav-toggle" class="nav-toggle">
				<span></span>
				<span></span>
				<span></span>
			</span>
			<div id="nav-menu" class="nav-right nav-menu">
				<a class="nav-item " href="/">
					Home
				</a>
				<a class="nav-item" href="/store">
					<span>Store</span>
				</a>
				<#if logged_in>
					<a class="nav-item" href="/account">
						<span>Account</span>
					</a>
					<a class="nav-item" href="/dashboard">
						<span>Dashboard</span>
					</a>
				<#else>
					<a class="nav-item" href="/account/login">
						<span>Login</span>
					</a>
				</#if>
			</div>
		</nav>
	</div>
</div>