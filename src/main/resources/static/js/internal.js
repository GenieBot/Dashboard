$(document).ready(function() {
	var data = JSON.parse($("#internal_data").html());
	var currentPage = $("#current_page").html();
    navbarTab(currentPage);
	processAlert(data);
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