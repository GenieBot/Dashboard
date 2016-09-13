$(document).ready(function () {
	var json = JSON.parse($("#internal_data").html());

	loadAlert(json);
});

function loadAlert(json) {
	if (!json.hasOwnProperty("alert")) {
		return;
	}
	var alertContent = json.alert;
	$("#alert").show();
	$("#alert-content").html(alertContent);
}