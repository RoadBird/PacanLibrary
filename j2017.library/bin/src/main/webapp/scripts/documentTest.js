var headerElement;
var menuElements;
var li;
var htmlTable;

var originLat;
var originLng;

function Book(title, author, year, description, coverPath, isbn) {
	this.title = title;
	this.author = author;
	this.year = year;
	this.description = description;
	this.coverPath = coverPath;
	this.isbn = isbn;
}
var json = '{ "books" : [{"title" : "My lovely book","author" : "Bob K. L.","year" : "1986.09.18","description" : "Lorem ipsum...","coverPath" : "http://MyLybrary/MyBook","isbn" : "sdv0834tknv"},{"title" : "My badly book","author" : "Max P. I.","year" : "1990.01.10","description" : "Lorem ipsum...","coverPath" : "http://Lybrary/MyBoo","isbn" : "afr87a9derh"},{"title" : "Not a book","author" : "Nope O. O.","year" : "1786.01.08","description" : "Lorem ipsum...","coverPath" : "http://MyBrary/Mook","isbn" : "adf9b8haydhb"}]}';
function getBooks() {
	var jsonObj = JSON.parse(json);
	for ( var key in jsonObj["books"]) {
		addBookInTable(jsonObj["books"][key]);
	}
}
function addBookInTable(book) {
	var tr = document.createElement("tr");
	htmlTable.appendChild(tr);
	var cover = document.createElement("td");
	tr.appendChild(cover);
	cover.innerHTML = book.coverPath;
	var title = document.createElement("td");
	tr.appendChild(title);
	title.innerHTML = book.author + "<br>" + book.title;
	var description = document.createElement("td");
	tr.appendChild(description);
	description.innerHTML = book.description;

}
function ready() {
	function success(position){
		originLat = position.coords.latitude;
		originLng = position.coords.longitude;
		console.log("Lat: " + originLat + ", Lng: " + originLng)
	}
	function error(){
		alert("Your browser doesn't support geolocation");
	}
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(success, error);
	} else {
		error();
	}
	
	$($("#menu")[0]).on("click", getUsers);

	headerElement = document.getElementById("Pe");
	menuElements = document.getElementsByClassName("menuBut");
	li = document.querySelector("li[myattr='myValue']");
	console.log(menuElements);
	console.log(li);

	headerElement.addEventListener("mousedown", function() {
		headerElement.style.fontWeight = "bold";

	});
	headerElement.addEventListener("mouseup", function() {
		headerElement.style.fontWeight = "normal";
	});
	headerElement.addEventListener("mouseout", function() {
		headerElement.style.fontWeight = "normal";
	});
	var table = document.getElementById("listBooks");
	htmlTable = document.createElement("table");
	table.appendChild(htmlTable);
	//setTimeout(getBooks, 10000);

	var possibleButton = $("button:contains('Tell me')");
	possibleButton.each(function() {
		$(this).on("click.1", function(event) {
			console.dir(event);
			alert("Me clicked")
		});
		$.each(this.attributes, function(iter, attr) {
			console.log(iter + " " + attr.name + ":" + attr.value);
		});
	});

	$("div.headerDiv li").each(function() {
		$(this).hover(function() {
			$(this).animate({
				"opacity" : "0"
			}, 300, "swing", function() {
				console.log("hidden");
			})
		}, function() {
			$(this).animate({
				"opacity" : "1"
			}, 300, "swing", function() {
				console.log("shown");
			})
		});
	});
	// Animate menu button
	$(".menuBut").each(function() {
		$(this).attr("isAnimating", "false");
		$(this).hover(function() {
			$(this).attr("isAnimating", "true");
			menuAnimate.call(this);
		}, function() {
			$(this).attr("isAnimating", "false");
			menuAnimateBack.call(this);
		});
	});
	function menuAnimate() {
		$(this).animate({
			"bottom" : "+=10"
		}, {
			step : function(animateStep) {
				if ($(this).attr("isAnimating") == "false") {
					$(this).stop();
				}
			},
			complete : function() {
				menuAnimateBack.call(this);
				menuAnimate.call(this);
			}
		});
	}
	function menuAnimateBack() {
		$(this).animate({
			"bottom" : "0"
		}, 200, "swing");
	}
	//
	var requestInterval = -1;
	$("div#filtersBooks select").each(function() {
		$(this).change(function(event) {
			clearTimeout(requestInterval);
			requestInterval = setTimeout(function() {
				console.log("Now I try make request");
			}, 3000)
			console.log(this + " select is changed");
		})
	});
}

function getUsers() {
	var url = "https://jsonplaceholder.typicode.com/users";
	var settings = {
		"beforeSend" : function(xhr) {
			xhr.setRequestHeader("Accept-Language", "en");
		},
		"success" : function(result, status, xhr) {
			var resultPars=JSON.parse(result);
			userTable(resultPars);

		},
		"error" : function(xhr, status, error) {
			console.log("Error getting users: " + error);
		},
		"dataType" : "text",
		"timeout" : 5000
	};

	$.ajax(url, settings);

}

function userTable(users){
	$("#listBooks").empty();
	let container = $("#listBooks").append("<table></table>");
	let table=$(container).find("table");
	$(table).append("<tr><td>"+"Name:"+"</td><td>"+"Email:"+"</td><td>"+"Distance:"+"</td></tr>");
	for(var i=0; i<users.length; i++){
		$(table).append("<tr><td>"+users[i]["name"]+
				"</td><td>"+users[i]["email"]+
				"</td><td>"+getDistance(users[i].address.geo.lat, users[i].address.geo.lng)+"</td></tr>");
		
		
	}
	
}
function degreesToRadians(degrees) {
	  return degrees * Math.PI / 180;
}
function getDistance(lat, lng){
	if(originLat, originLng){
	  var earthRadiusKm = 6371;
	  var dLat = degreesToRadians(originLat-lat);
	  var dLng = degreesToRadians(originLng-lng);

	  lat = degreesToRadians(lat);
	  originLat = degreesToRadians(originLat);

	  var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	          Math.sin(dLng/2) * Math.sin(dLng/2) * Math.cos(lat) * Math.cos(originLat); 
	  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	  return (earthRadiusKm * c).toFixed(2);
	} else {
		return "-------"
	}
}

$(document).ready(ready);
