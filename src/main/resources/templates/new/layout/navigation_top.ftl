<div class="hero-head">
	<div class="container">
		<nav class="nav">
			<div class="nav-left">
				<a class="nav-item is-active" href="/">
					<span style="font-size: 23px; letter-spacing: 4px; font-weight: bold">GENIE</span>
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
					<span class="tag is-small is-success">50% OFF!</span>
				</a>
				<span class="nav-item">
					<#if logged_in>
						<a class="button is-light is-outlined" href="/account">
							<span class="icon">
								<i class="fa fa-user"></i>
							</span>
							<span>Account</span>
						</a>
						<a class="button is-light is-outlined" href="/dashboard">
							<span class="icon">
								<i class="fa fa-dashboard"></i>
							</span>
							<span>Dashboard</span>
						</a>
					<#else>
						<a class="button is-light is-outlined" href="/login">
							<span class="icon">
								<i class="fa fa-user"></i>
							</span>
							<span>Login</span>
						</a>
					</#if>
				</span>
			</div>
		</nav>
	</div>
</div>