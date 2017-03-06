<%@page import="com.gcit.lms.domain.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.service.AdministrativeService" %>
<%@ page import="com.gcit.lms.domain.Author" %>
<%
    AdministrativeService service = new AdministrativeService(); 
    List<Author> authors = new ArrayList<Author>();
    authors = service.viewAuthors();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>LMS</title>
    </head>
    <body>
    <h2>Welcome to GCIT Library Management System</h2>
    <h3>Hello Admin! Enter Author details</h3>
        <form action="addAuthor" method="post">
            Enter Author Name: <input type="text" id = "authorName" name="authorName">
            <button type="submit" >Add Author</button>
        </form>
    </body>
</html>