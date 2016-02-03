package com.dao;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.sql.NCLOB;

public class DAOImpl implements DAOInterface {
	Connection con;
	
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
	public void clearTable() {
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
			e.printStackTrace();
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
	
	public void getContent(int id) {
		//TODO
		
		//System.out.println(myClob.length());
		//System.out.println(myClob.getSubString(1, (int) myClob.length()));
	}
	
}
