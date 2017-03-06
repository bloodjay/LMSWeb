/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gcit.lms.dao;

import com.gcit.lms.domain.Genre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jay
 */
public class GenreDAO extends BaseDAO{
    
	public GenreDAO(Connection conn) {
		super(conn);
	}

	public List<Genre> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_genre", null);
	}
        
        public void insertGenre(Genre gen) throws ClassNotFoundException,SQLException
        {
               save("insert into tbl_genre(genre_id,genre_name) values(?,?)", new Object[] {gen.getGenre_id(),gen.getGenre_name()});
        }

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		List<Genre> publishers = new ArrayList<Genre>();
		while(rs.next()){
			Genre p = new Genre();
			p.setGenre_id(rs.getInt("genre_id"));
			p.setGenre_name(rs.getString("genre_name"));
			publishers.add(p);
		}
		return publishers;
	}

        public Genre readOne(int genId) throws Exception
        {
            List<Genre> Publishers = read("select * from tbl_genre where genre_id = ?", new Object[] {genId});
            if(Publishers != null && Publishers.size()>0)
            {return Publishers.get(0);}
            return null;
        }
	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> publishers = new ArrayList<>();
		while(rs.next()){
			Genre p = new Genre();
			p.setGenre_id(rs.getInt("genre_id"));
			p.setGenre_name(rs.getString("genre_name"));
			publishers.add(p);
		}
		return publishers;
	}
}
