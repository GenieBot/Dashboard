<#import "layout.ftl" as layout>
<@layout.main "Home" "home">
<style>
	.home-heading {
		text-decoration: underline;
        text-align: center;
	}
	.box {
		padding: 50px;
        text-align: center;
	}
	.header-image {
		width: 70%;
		border: 1px solid gray;
	}
</style>
<div class="row">
	<div class="col-lg-12">
		<center>
			<h1>SICK AS FUCK CHAT BOT</h1>
		</center>
	</div>
</div>
<hr />
<div class="row">
	<div class="col-lg-12 home-heading">
		<h2>What can it do?</h2>
	</div>
    <div class="col-lg-12">
        <div class="col-md-4 box">
            <i class="fa fa-dashboard fa-5x"></i>
            <h3>Dashboard</h3>
            <p>Do cool things dude</p>
        </div>
        <div class="col-md-4 box">
            <i class="fa fa-cloud fa-5x"></i>
            <h3>Cloud Hosted</h3>
            <p>Do cool things dude</p>
        </div>
        <div class="col-md-4 box">
            <i class="fa fa-magic fa-5x"></i>
            <h3>Commands</h3>
            <p>Do cool things dude</p>
        </div>
    </div>
	<div class="col-lg-12">
        <div class="col-md-4 box">
            <i class="fa fa-cogs fa-5x"></i>
            <h3>Customizable</h3>
            <p>Do cool things dude</p>
        </div>
        <div class="col-md-4 box">
            <i class="fa fa-gavel fa-5x"></i>
            <h3>Moderation</h3>
            <p>Do cool things dude</p>
        </div>
        <div class="col-md-4 box">
            <i class="fa fa-cubes fa-5x"></i>
            <h3>Cross Platform</h3>
            <p>Do cool things dude</p>
        </div>
	</div>
</div>
</@layout.main>