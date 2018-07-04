function loadViaJquery(method, url, errorHandler, loadHandler){
	
}
//Request example 
function loadViaCore(){
	var url = "https://jsonplaceholder.typicode.com/photos";
	var url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Orion_Nebulas_biggest_stars.jpg/799px-Orion_Nebulas_biggest_stars.jpg";
	var errorHandler = function(){
		console.log("Fail. Status: " + this.status);
		//console.log(this.statusText);
	}
	var loadHandler = function(){
		console.log("Load compleated. Status: " + this.status);
		
	}
	var xmlhtml = new XMLHttpRequest();
	xmlhtml.addEventListener("error", errorHandler);
	xmlhtml.addEventListener("load", loadHandler);
	xmlhtml.addEventListener("progress", function(event){
		console.log(Math.floor((event.loaded / event.total) * 100));
	});
	xmlhtml.open("GET", url, true);
	xmlhtml.send();
}
function ready2(){
	var somePe = $(".menuDiv .menuBut");
	//$(".menuDiv .menuBut").click(function() {console.log("Are you clicked on me?")});
	somePe.each(function(){ $(this).click(menuClick)});
}
function menuClick(){
	console.dir(this);
	console.log(this.id);
}
$(document).ready(ready2);
