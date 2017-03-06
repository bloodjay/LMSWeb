package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.gcit.lms.domain.Author;
public abstract class BaseDAO {
	
	public Connection connection;
	
	public BaseDAO(Connection conn){
		this.connection = conn;
	}
	
	public void save(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		
		PreparedStatement pstmt = connection.prepareStatement(query);
		
		if(vals !=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count ++;
			}
		}
		pstmt.executeUpdate();
	}
	
public Integer saveWithID(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		
		PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		if(vals !=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count ++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return -1;
		}
	}
	
	
	public <T> List<T> read(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = connection.prepareStatement(query);
		if(vals !=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count ++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		
		return (List<T>) extractData(rs);
	}

	public abstract List<?> extractData(ResultSet rs) throws SQLException;
	
	public <T> List<T> readFirstLevel(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = connection.prepareStatement(query);
		if(vals !=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count ++;
			}
		}
		ResultSet rs = pstmt.executeQuery();		
		return (List<T>) extractDataFirstLevel(rs);
	}

	public abstract List<?> extractDataFirstLevel(ResultSet rs) throws SQLException;
}
