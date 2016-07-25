<#import "dashboard_layout.ftl" as layout>
<@layout.dashboard
content_title="Manage platforms"
content_subtitle="Here you can manage your platforms!"
bottom_tabs={
"Skype": "/platforms/skype",
"Discord": "/platforms/discord",
"Slack": "/platforms/slack",
"Telegram": "/platforms/telegram",
"+": ["/platforms/manage", true]
}
>
<div class="columns">
    <div class="column is-one-third">
        <div class="card is-fullwidth">
            <header class="card-header">
                <p class="card-header-title" style="font-size: 15px;">
                    Skype
                </p>
                <a class="card-header-icon">
                    <i class="fa fa-skype fa-2x"></i>
                </a>
            </header>
            <div class="card-content">
                <div class="content">
                    <p>3 active networks</p>
                </div>
            </div>
            <footer class="card-footer">
                <a href="/platforms/manage/skype" class="card-footer-item">Remove</a>
            </footer>
        </div>
    </div>
    <div class="column is-one-third">
        <div class="card is-fullwidth">
            <header class="card-header">
                <p class="card-header-title" style="font-size: 15px;">
                    Discord
                </p>
            </header>
            <div class="card-content">
                <div class="content">
                    <p>Add it now!</p>
                </div>
            </div>
            <footer class="card-footer">
                <a href="/platforms/manage/skype" class="card-footer-item">Add</a>
            </footer>
        </div>
    </div>
    <div class="column is-one-third">
        <div class="card is-fullwidth">
            <header class="card-header">
                <p class="card-header-title" style="font-size: 15px;">
                    Slack
                </p>
                <a class="card-header-icon">
                    <i class="fa fa-slack fa-2x"></i>
                </a>
            </header>
            <div class="card-content">
                <div class="content">
                    <p>Add it now!</p>
                </div>
            </div>
            <footer class="card-footer">
                <a href="/platforms/manage/slack" class="card-footer-item">Add</a>
            </footer>
        </div>
    </div>
</div>
<div class="columns">
    <div class="column is-one-third">
        <div class="card is-fullwidth">
            <header class="card-header">
                <p class="card-header-title" style="font-size: 15px;">
                    Telegram
                </p>
                <a class="card-header-icon">
                    <i class="fa fa-telegram fa-2x"></i>
                </a>
            </header>
            <div class="card-content">
                <div class="content">
                    <p>Add it now!</p>
                </div>
            </div>
            <footer class="card-footer">
                <a href="/platforms/manage/telegram" class="card-footer-item">Add</a>
            </footer>
        </div>
    </div>
</div>
</@layout.dashboard>