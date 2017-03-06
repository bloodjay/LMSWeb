package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Publisher;

@SuppressWarnings("unchecked")
public class AuthorDAO extends BaseDAO{

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void insertAuthor(Author author) throws ClassNotFoundException, SQLException{
		save("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException{
		save("delete from tbl_author where authorId=?", new Object[] {author.getAuthorId()});
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException{
		save("delete * from tbl_author", null);
	}
	
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException{
		save("update  tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	
	public List<Author> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_author", null);
	}

                public Author readOne(int AuthId) throws Exception
        {
            List<Author> Publishers = read("select * from tbl_author where authorId = ?", new Object[] {AuthId});
            if(Publishers != null && Publishers.size()>0)
            {return Publishers.get(0);}
            return null;
        }
        
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		BookDAO bdao = new BookDAO(connection);
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			try {
				a.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN(select bookId from tbl_book_authors where authorId = ?)", new Object[]{a.getAuthorId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			authors.add(a);
		}
		return authors;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}
}
