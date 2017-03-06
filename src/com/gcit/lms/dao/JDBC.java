package com.gcit.lms.dao;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBC {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
                Author au = new Author();
//                au.setAuthorName("helppo");
                AdministrativeService ser = new AdministrativeService();
//                ser.addAuthor(au);
 //               List<Author> a = new ArrayList<>();
 //               a = ser.viewAuthors();
                 
                List<Book> b = new ArrayList<>();
                Book boo = new Book();
                boo.setTitle("title");
                b = ser.viewBook();

                Publisher pub = new Publisher();
                pub.setPublisherId(2);
                au.setAuthorId(2);
                
                boo.setPublisher(pub);

                List<Genre> g = new ArrayList<>();
                Genre gen = new Genre();
                gen.setGenre_id(1);
                g.add(gen);
                boo.setGenres(g);
                ser.createBook(boo);

                
                System.out.println(b.get(0).getAuthors().size());
                
                
                for(int i = 0; i <b.get(0).getAuthors().size();i++)
                System.out.println(b.get(0).getAuthors().get(i).getAuthorName());
                
	}
}
