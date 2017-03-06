package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet("/addAuthor")
public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    AdministrativeService service = new AdministrativeService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorName = request.getParameter("authorName");
        String booktitle = request.getParameter("book");
        String authna = request.getParameter("aname");               
        String pid = request.getParameter("pubname");
        String pname = request.getParameter("pname");
        String pubadd = request.getParameter("pubadd");
        String pubpho = request.getParameter("pubpho");
        String gered = request.getParameter("genrename");
        
        if (!(authorName == null)) {
            try {
                Author author = new Author();
                author.setAuthorName(authorName);
                service.createAuthor(author);
                request.setAttribute("message", "Author added sucessfully");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                request.setAttribute("message", "Author failed sucessfully");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (!(booktitle == null)) {
            try {
                booktitle = request.getParameter("book");
                Book boo = new Book();
                boo.setTitle(booktitle);
                Publisher pub = new Publisher();
                Author auth = new Author();
                Genre gen = new Genre();
                List <Author> authlist = new ArrayList<Author>();
                List <Genre> genr = new ArrayList<Genre>();
                try {
                    pub=service.findpublisher(Integer.parseInt(pid));
                    auth=service.findauthor(Integer.parseInt(authna));
                    gen=service.findgenre(Integer.parseInt(gered));
                } catch (Exception ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
               authlist.add(auth);
               genr.add(gen);
                boo.setPublisher(pub);
                boo.setAuthors(authlist);
                boo.setGenres(genr);
                service.createBook(boo);
                request.setAttribute("message", pub.getPublisherId()+" AID "+auth.getAuthorId()+" GID "+gen.getGenre_id()+" BID "+ boo.getBookId());
                 } catch (ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("message", "Book added failed");
                 } catch (SQLException ex) {
                ex.printStackTrace();
            }           
        }
        
        else if(!(pname==null))
        {      Publisher pub = new Publisher();
            try{
                pub.setPublisherName(pname);
                pub.setPublisherAddress(pubadd);
                pub.setPublisherPhone(pubpho);       
                service.createPub(pub);
               }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }                           
        }
        RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
        rd.forward(request, response);
    }
}
