<%-- 
    Document   : viewgenre
    Created on : Jun 20, 2016, 10:12:54 PM
    Author     : jay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Genre" %>
    <%
        AdministrativeService service = new AdministrativeService();
        List<Genre> gen = new ArrayList<Genre>();
        gen = service.viewgrenre();
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>   
    <body>
        <table>
	<tr>
	<th>GenreID</th>
	<th>GenreName</th>
	</tr>
	<%for(Genre a: gen){ %>
		<tr>
			<td align="center"><%=a.getGenre_id()%></td>
                        <td align="center"><%=a.getGenre_name()%></td>
		</tr>
	<%} %>	
        </table>       
    </body>
</html>
