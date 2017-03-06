package com.gcit.lms.service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministrativeService {
	
	ConnectionUtil util = new ConnectionUtil();
	
	public void createAuthor(Author author) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.insertAuthor(author);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public void createBook(Book book) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);                       
			bdao.insertBook(book);			
			conn.commit();
		}catch (Exception e){
			System.out.println(e);
			conn.rollback();
		}finally{
			conn.close();
		}
	}
	
	public List<Author> viewAuthors() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
        
        public void createPub(Publisher pub) throws ClassNotFoundException, SQLException{
            Connection conn = util.getConnection();
            try{
                PublisherDAO pdao = new PublisherDAO(conn);
                pdao.insertPub(pub);
                conn.commit();               
                }catch(Exception e)
                    {e.printStackTrace();}         
               }        
        	public List<Book> viewBook() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}                             
                
                public List<Publisher> viewpub() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}
                
                public List<Genre> viewgrenre() throws ClassNotFoundException, SQLException 
                {
                    Connection conn = util.getConnection();
                    try{
                        GenreDAO gdao = new GenreDAO(conn);
                        return gdao.readAll();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        conn.close();
                    }
                    return null;
                }
        
                
        public void addAuthor(Author au) throws ClassNotFoundException, SQLException{
         Connection conn = util.getConnection();
         try{
             AuthorDAO adao = new AuthorDAO(conn);
             adao.insertAuthor(au);
             conn.commit();
            }      
         catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}

}
       public Publisher findpublisher(int pubId) throws Exception 
       {  Connection conn = null;
            try {
                conn = util.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdministrativeService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdministrativeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return (new PublisherDAO(conn).readOne(pubId));
       }
       
       public Author findauthor(int authId) throws Exception
       {
           Connection conn = null;
                 try {
                conn = util.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdministrativeService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdministrativeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return (new AuthorDAO(conn).readOne(authId));
       }
       
       public Genre findgenre(int genid) throws Exception
       {
           Connection conn = null;
           try{
               conn = util.getConnection();
           }
           catch (ClassNotFoundException ex){
                         Logger.getLogger(AdministrativeService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdministrativeService.class.getName()).log(Level.SEVERE, null, ex);
            }
           return(new GenreDAO(conn).readOne(genid));
       }
}
