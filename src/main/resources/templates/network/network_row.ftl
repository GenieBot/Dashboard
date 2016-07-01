<#macro network name="Default" id="default">
<tr>
    <td>${name}</td>
    <td>${id}</td>
    <td>
        <a href="/manage_network?network=${id}">
            <div class="btn btn-sm btn-default">Manage</div>
        </a>
        <a href="/remove_network?network=${id}">
            <div class="btn btn-sm btn-danger">Remove</div>
        </a>
    </td>
</tr>
</#macro>