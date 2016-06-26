$(document).ready(function() {
	var data = JSON.parse($("#internal_data").html());
	var currentPage = $("#current_page").html();
	processAlert(data);

	// temp shit
	if (currentPage == "home") {
        {
            var element = $("#login_button");
            element.text("Logout");
            element.attr("href", "/logout");
        }
        {
            var element = $("#account_button");
            element.show();
        }
    }
});

function processAlert(data) {
	if (!data.hasOwnProperty("alert")) {
		return;
	}
	$("#alert").show();
	$("#alert-content").text(data.alert);
}