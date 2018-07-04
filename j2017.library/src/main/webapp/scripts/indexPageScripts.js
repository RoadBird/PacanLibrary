var authorFilter;
var titleFilter;
var publishFirstYearFilter;
var publishLastYearFilter;

$(document).ready(function() {
	authorFilter = $("#authorFilter");
	titleFilter = $("#titleFilter");
	publishFirstYearFilter = $("#publishFirstYearFilter");
	publishLastYearFilter = $("#publishLastYearFilter");
	$("#filterButton").click(filterBooksRequest);
	$("#authorFilter").autocomplete({
        minLength: 1,
        source: function(request, response) {
            $.ajax({
                url: "filterAuthors",
                dataType: "json",
                delay: 500,
                data: request,
                success: function(data, textStatus, jqXHR) {
                    response(data);
                },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log(textStatus);
                }
            });
        }
    });
	$("#authorFilter").on("autocompleteselect", function (e, ui) {
		authorValue = ui.item.value;
    });
	$("#titleFilter").autocomplete({
        minLength: 1,
        source: function(request, response) {
            $.ajax({
                url: "filterBooks",
                dataType: "json",
                data: {
                	term : request.term,
                	author : authorFilter.val()
                },
                delay: 500,
                success: function(data, textStatus, jqXHR) {
                    response(data);
                },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log(textStatus);
                }
            });
        }
    });
});

function filterBooksRequest() {
	$.ajax({
		"url" : "filter",
		"type" : "POST",
		"success" : function(result, status, xhr) {
			console.log("Successfull request: " + result);
		},
		"error" : function(xhr, status, error) {
			console.log("Error geting books: " + error);
		},
		data : JSON.stringify({
			"author" : authorFilter.val(),
			"title" : titleFilter.val(),
			"firstPublished" : publishFirstYearFilter.val(),
			"lastPublished" : publishLastYearFilter.val()
		})
	});
}
