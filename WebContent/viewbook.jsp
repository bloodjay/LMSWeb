<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.service.AdministrativeService" %>
<%@ page import="com.gcit.lms.domain.Book" %>

<%AdministrativeService service = new AdministrativeService();
    List<Book> book = new ArrayList<Book>();
    book = service.viewBook();
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
            <th>Book ID</th>
            <th>Book Title</th>
            <th>Publish </th>
            <th>Author name</th>
            <th>Genre</th>
        </tr>
        <%for (Book a : book) {%>
        <tr>
            <td align="center"><a href = "displaybook.jsp"><%=a.getBookId()%></a></td>
            <td align="center"><%=a.getTitle()%></td>
            <% String publisher = "";
                if (a.getPublisher() != null) {
                    publisher = a.getPublisher().getPublisherName();
                }%>
            <td align="center"><%=publisher%></td>

            <% 
                if (a.getAuthors() != null) {
                    try {
                        for (Author b : a.getAuthors()) {%>

            <td><%=b.getAuthorName()%></td>
            
            
            <% }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            %>
            
            <% 
                if (a.getGenres() != null) {
                    try {
                        for (Genre b : a.getGenres()) {%>


            <td><%=b.getGenre_name()%></td>
            
            <% }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            %>

        </tr>
        <%} %>	    	
    </table>
</html>