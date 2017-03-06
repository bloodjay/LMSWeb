package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO{
	
	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public List<Publisher> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_publisher", null);
	}
        
        public void insertPub(Publisher pub) throws ClassNotFoundException,SQLException
        {
               save("insert into tbl_publisher(publisherName,publisherAddress,publisherPhone) values(?,?,?)", new Object[] {pub.getPublisherName(),pub.getPublisherAddress(),pub.getPublisherPhone()});
        }

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
                        p.setPublisherAddress(rs.getString("publisherAddress"));
                        p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		return publishers;
	}

        public Publisher readOne(int PublisherId) throws Exception
        {
            List<Publisher> Publishers = read("select * from tbl_publisher where publisherId = ?", new Object[] {PublisherId});
            if(Publishers != null && Publishers.size()>0)
            {return Publishers.get(0);}
            return null;
        }
	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			publishers.add(p);
		}
		return publishers;
	}

}
