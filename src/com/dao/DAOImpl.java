package com.dao;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.exception.DatabaseException;

/**
 * Class that implements all database operations to be used by the application
 * @author Rajat
 *
 */
public class DAOImpl implements DAOInterface {
	Connection con;
	
	/**
	 * 
	 * @param con Connection to the database
	 */
	public DAOImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public void addLink(String url, String content, int refId) {
		try {
			PreparedStatement ps = con.prepareStatement("insert into links(url, content, refid) values(?, ?, ?)");
			ps.setString(1, url);
			Clob myClob = con.createClob();
			myClob.setString(1, content);
			ps.setClob(2, myClob);
			ps.setInt(3, refId);
			ps.executeUpdate();
			ps.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void clearTable() throws DatabaseException {
		try {
			PreparedStatement ps = con.prepareStatement("truncate table links");
			ps.executeUpdate();
			ps = con.prepareStatement("drop sequence links_seq");
			ps.executeUpdate();
			ps = con.prepareStatement("create sequence links_seq");
			ps.executeUpdate();
			ps.close();
		}
		catch(SQLException e) {
			throw new DatabaseException();
		}
	}
	
	@Override
	public boolean linkExists(String link) {
		try {
			PreparedStatement ps = con.prepareStatement("select * from links where url=?");
			ps.setString(1, link);
			ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				rs.close();
				ps.close();
				return true;
			}
			else {
				rs.close();
				ps.close();
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public int getId(String url) {
		try {
			PreparedStatement ps = con.prepareStatement("select id from links where url = ?");
			ps.setString(1, url);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				rs.close();
				ps.close();
				return id;
			}
			else {
				rs.close();
				ps.close();
				return -1;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public ResultSet getAllContent() {
		try {
			PreparedStatement ps = con.prepareStatement("select id, content from links order by id");
			ResultSet rs = ps.executeQuery();
			return rs;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getFrequency(String term) {
		try {
			int frequency = 0;
			PreparedStatement ps = con.prepareStatement("select count(*) from links where content like \'%" + term + "%\'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				frequency = rs.getInt(1);
			}
			return frequency;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public int countDocuments() {
		try {
			PreparedStatement ps = con.prepareStatement("select count(*) from links");
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while(rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			ps.close();
			return count;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public String getURL(int id) {
		try {
			PreparedStatement ps = con.prepareStatement("select url from links where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String url = rs.getString(1);
				rs.close();
				ps.close();
				return url;
			}
			rs.close();
			ps.close();
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getContent(int id) {
		try {
			PreparedStatement ps = con.prepareStatement("select content from links where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Clob content = rs.getClob(1);
				String text = content.getSubString(1, (int)content.length());
				return text;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
