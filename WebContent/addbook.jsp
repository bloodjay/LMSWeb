<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.dao.PublisherDAO"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcit.lms.service.AdministrativeService" %>
<%@ page import="com.gcit.lms.domain.Author" %>
<%AdministrativeService service = new AdministrativeService();
    List<Author> authors = new ArrayList<Author>();
    List<Publisher> pub = new ArrayList<Publisher>();
    List<Genre> gen = new ArrayList<Genre>();
    authors = service.viewAuthors();
    pub = service.viewpub();
    gen = service.viewgrenre();
%>
<!DOCTYPE html>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
    </head>
    <body>
        <h1>Please input the name of the book</h1>
        <form action = "addAuthor" method="post">
            Enter: the name of the book: <input type = "text" name = "book">         
            <button type="submit" >submit</button>           
            <div><p>select author name</p>
                <select name ="aname">               
                    <%for (Author a : authors) {%>	
                    <option value=<%=a.getAuthorId()%> >
                        <%=a.getAuthorName()%>
                    </option>			
                    <%} %>        
                </select>
            </div>
            <div>
                <p>select publisher name</p>
                <select name = "pubname">
                    <%for (Publisher a : pub) {%>	
                    <option value=<%=a.getPublisherId()%> >
                        <%=a.getPublisherName()%>            
                    </option>			
                    <%} %>   
                </select>
            </div>
            <div>
                <p>select genre</p>
                <select name = "genrename">
                    <%for (Genre a : gen) {%>	
                    <option value=<%=a.getGenre_id()%> >
                        <%=a.getGenre_name()%>            
                    </option>			
                    <%}%>   
                </select>
            </div>
        </form>
    </body>
</html>

