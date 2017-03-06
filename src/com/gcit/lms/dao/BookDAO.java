package com.gcit.lms.dao;

import com.gcit.lms.domain.Author;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDAO extends BaseDAO {
    
	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public List<Book> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_book", null);
	}

       	public void insertBook(Book book) throws ClassNotFoundException, SQLException{
//            save("insert into tbl_book (title,pubId) values(?,?)",new Object[] { book.getTitle(),book.getPublisher().getPublisherId()});
            int bookId = saveWithID("insert into tbl_book (title,pubId) values(?,?)",new Object[] { book.getTitle(),book.getPublisher().getPublisherId()});
            for(Author a: book.getAuthors())
            {
                save("insert into tbl_book_authors (bookId, authorId) values (?,?)",new Object[]{bookId,a.getAuthorId()});
            }
            for(Genre a: book.getGenres())
            {
                save("insert into tbl_book_genres (genre_id,bookId) values(?,?)", new Object[]{a.getGenre_id(),bookId});
            }            
	}
                
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pdao = new PublisherDAO(connection);
		AuthorDAO adao = new AuthorDAO(connection);
                GenreDAO gdao = new GenreDAO(connection);
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			try {
                               b.setPublisher(pdao.readOne(rs.getInt("pubId")));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception ex) {
                           System.out.print("error1");
                        Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
			try {
				b.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN(select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()}));
                                //b.setGenres(gdao.readFirstLevel("select * from tbl_genre where genre_id IN(select genre_id from tbl_book_genres where genre_id = ?)", new Object[]{b.getBookId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			books.add(b);
		}
		return books;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
}
