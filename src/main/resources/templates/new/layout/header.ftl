<div class="hero-body">
    <div class="container">
        <div class="columns is-vcentered">
            <div class="column">
	            <#if header_link?has_content>
                    <a href="${header_link}">
                        <p class="title">
	                        ${header_title}
                        </p>
                    </a>
	            <#else>
                    <p class="title">
		                ${header_title}
                    </p>
	            </#if>
                <p class="subtitle">
                    ${header_subtitle}
                </p>
            </div>
            <div class="column is-narrow">
                <div class="box">
                    meme
                </div>
            </div>
        </div>
    </div>
</div>