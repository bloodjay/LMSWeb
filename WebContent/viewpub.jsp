    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Publisher" %>
    <%AdministrativeService service = new AdministrativeService(); 
    List<Publisher> book = new ArrayList<Publisher>();
    book = service.viewpub();
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
	<th>pub name</th>
	<th>pub address</th>
	<th>pub phone </th>
	</tr>
	<%for(Publisher a: book){ %>
		<tr>
                        <td align="center"><a href = "viewpub.jsp"><%=a.getPublisherId() %></a></td>
			<td align="center"><%=a.getPublisherName()%></td>
                        <td align="center"><%=a.getPublisherAddress()%></td>
                        <td align="center"><%=a.getPublisherPhone()%></td>
		</tr>
	<%} %>	
        </table>
    </body>
</html>
