<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Author" %>
    <%AdministrativeService service = new AdministrativeService(); 
    List<Author> authors = new ArrayList<Author>();
    authors = service.viewAuthors();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
</head>
<h2>Welcome to GCIT Library Management System</h2>
<h3>List of Authors</h3>
<table>
	<tr>
	<th>Author ID</th>
	<th>Author Name</th>
	<th>Edit Author</th>
	<th>Delete Author</th>
	</tr>
	<%for(Author a: authors){ %>
		<tr>
			<td align="center"><%=a.getAuthorId() %></td>
			<td align="center"><%=a.getAuthorName() %></td>
			<td align="center"><button type="button" onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId()%>'">EDIT</button></td>
			<td align="center"><button type="button" onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td>
		</tr>
	<%} %>	
	
</table>
</html>