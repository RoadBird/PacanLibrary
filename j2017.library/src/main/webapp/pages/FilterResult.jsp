<%@page import="j2017.library.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles/libraryCSS.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="scripts/indexPageScripts.js"></script>
<%@ taglib prefix = "ex" uri = "../WEB-INF/custom.tld"%>
<title>Library</title>
</head>
<body>
	<ex:HeadTag/>
	<div id="listBooks">
	<%
		List<Book> bookList = (List<Book>) request.getAttribute("books");
		if (bookList != null && !bookList.isEmpty()) {
	%>
	<table border="1">
		<caption>Books</caption>
		<tr>
			<th>ID</th>
			<th>Author</th>
			<th>Title</th>
			<th>Description</th>
		</tr>
		<%
			for (Book book : bookList) {
		%>
		<tr>
			<td>
				<%
					out.print(book.getId());
				%>
			</td>
			<td>
				<%
					out.print(book.getAuthor().toString());
				%>
			</td>
			<td>
				<%
					out.print(book.getTitle());
				%>
			</td>
			<td>
				<%
					out.print(book.getDescription());
				%>
			</td>
		</tr>
		<% } %>		 
		</table>
		<%
			} else {
		%>
		<h3>There are no results for your request</h3>
		<%
			}
		%>
	</div>
	<ex:FootTag/>
</body>
</html>