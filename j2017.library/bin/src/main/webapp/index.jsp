<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" content="library">
<link rel="stylesheet" href="styles/libraryCSS.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="scripts/indexPageScripts.js"></script>
<title>Library</title>
</head>
<body>
	<div class="headerDiv">
		<img alt="Book" src="http://icons.veryicon.com/256/System/Line/Open%20Book.png">
		<div id="headerText">
		<p id="Pe" >
			MY BEST LIBRARY</p>
			<div id="headerContacts">
				Contacts
				<ul>
					<li myattr="myValue">Contact phone</li>
					<li>Contact email</li>
					<li>Contact skype</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="menuDiv">
		<p class="menuBut" id="menu">MENU</p>
		<p>|</p>
		<p class="menuBut" id="add">ADD</p>
		<p>|</p>
		<p class="menuBut" id="remove">REMOVE</p>
		<p>|</p>
		<p class="menuBut" id="edit">EDIT</p>
		<p>|</p>
		<p class="menuBut" id="text">TEXT</p>
	</div>

	<div class="booksDiv">
		<div id="filtersBooks">
			<p>Filters:</p><br>
			<p>Author name: </p>
			<p><input id="authorFilter">
			</p>
			<p>Title book: </p>
			<p><input id="titleFilter">
			</p>
			<p>Publish year: </p>
			<p><input id="publishYearFilter">
			</p>
			<button id="filterButton">Filter</button>
		</div>
		<div id="listBooks">
		</div>
	</div>

	<div class="footerDiv">
		<p>Copyright...</p>
	</div>
</body>
</html>

