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
			<p>Filters:</p>
			<p>
				<select id="authorFilter">
					<option value="s1.1" selected>s1.1</option>
					<option value="s1.2" selected>s1.2</option>
					<option value="s1.3" selected>s1.3</option>
				</select>
			</p>
			<p>
				<select id="titleFilter">
					<option value="s2.1" selected>s2.1</option>
					<option value="s2.2" selected>s2.2</option>
					<option value="s2.3" selected>s2.3</option>
				</select>
			</p>
			<p>
				<select id="publishYearFilter">
					<option value="s3.1" selected>s3.1</option>
					<option value="s3.2" selected>s3.2</option>
					<option value="s3.3" selected>s3.3</option>
				</select>
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

