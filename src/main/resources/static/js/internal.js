$(document).ready(function() {
	var data = JSON.parse($("#internal_data").html());
	var currentPage = $("#current_page").html();
    navbarTab(currentPage);
	processAlert(data);
	if (currentPage === "networks") {
		processNetworks(data);
	}
});

function navbarTab(currentPage) {
	var element = $("#" + currentPage + "_tab");
    if (element.length > 0) {
        element.addClass("active");
    }
}

function processAlert(data) {
	if (!data.hasOwnProperty("alert")) {
		return;
	}
	$("#alert").show();
	$("#alert-content").text(data.alert);
}

function processNetworks(data) {
	var platform = data.platform;
	var networks = data.networks;
	var table = $("#networks_table");
	for (var i = 0; i < networks.length; i++) {
		var obj = networks[i];
		table.append("<tr><td>" + obj.name + "</td><td>" + obj.id + "</td><td><a href=\"/manage_network?platform=" + platform + "&network=" + obj.id + "\"><div class=\"btn btn-sm btn-default\">Manage</div></a> <a href=\"/remove_network?platform=" + platform + "&network=" + obj.id + "\"><div class=\"btn btn-sm btn-danger\">Remove</div></a></td></tr>");
	}
}