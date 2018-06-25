var authorFilter;
var titleFilter;
var publishYearFilter;

$(document).ready(function() {
	authorFilter = $("#authorFilter")[0];
	titleFilter = $("#titleFilter")[0];
	publishYearFilter = $("#publishYearFilter")[0];
	$("#filterButton").click(filterBooksRequest)
}

);

function filterBooksRequest() {
	var authorInput = authorFilter.value;
	var titleInput = titleFilter.value;
	var yearInput = publishYearFilter.value;
	$.ajax({
		url : "filterbooks",
		"type" : "POST",
		"success" : function(result, status, xhr) {
			console.log("Successfull request: " + result);

		},
		"error" : function(xhr, status, error) {
			console.log("Error geting books: " + error);
		},
		data : JSON.stringify({
			"author" : authorInput,
			"title" : titleInput,
			"published" : yearInput
		})
	});
}
