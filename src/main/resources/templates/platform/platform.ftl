<#macro platform id="default" heading="Default">
<div class="col-md-3">
    <div class="panel panel-default">
	    <div class="panel-heading">
	        ${heading}
	    </div>
        <div class="panel-body">
            <a href="/networks?platform=${id}">
                <div class="btn btn-block btn-default">Manage networks</div>
            </a>
	        <br />
	        <form action="/delete_platform" method="post">
		        <input type="hidden" name="platform" value="${id}">
                <button type="submit" class="btn btn-block btn-danger">Remove platform</button>
	        </form>
        </div>
    </div>
</div>
</#macro>