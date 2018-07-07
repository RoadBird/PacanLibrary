<%@page import="j2017.library.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
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
		<%
			}
			} else {
		%>
		<h3>There are no results for your request</h3>
		<%
			}
		%>
	</table>
</body>
</html>