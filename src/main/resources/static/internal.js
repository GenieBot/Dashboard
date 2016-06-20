$(document).ready(function() {
	var data = JSON.parse($("#internal_data").html());
	var currentPage = $("#current_page").html();
	processAlert(data);

	if (currentPage == "home") {
		var element = $("#login_button");
		element.text("Logout");
		element.attr("href", "/logout");
	}
});

function processAlert(data) {
	if (!data.hasOwnProperty("alert")) {
		return;
	}
	var element = $(".alert");
	element.text(data.alert);
	element.show();
}