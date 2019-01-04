package com.example.java.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataSourceConnect {

	
	private String jdbcdriver;
	private String dburl;
	
	private String user;
	private String pass;
	
	private Connection conn = null;

	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		loadDriver();
		if(conn==null)
		{
		System.out.println("Connecting to Database..."+dburl);
		conn=DriverManager.getConnection(dburl,user,pass);
		System.out.println("Connection Created");
		}
		return conn;

	}
	private void loadDriver() throws ClassNotFoundException {
		Class.forName(jdbcdriver);
		System.out.println("Database Driver loaded");
	}
	public void closeConnection() {
		if (conn != null) {
			try {
				System.out.println("Closing connection...");
				conn.close();
				System.out.println("Connection closed");				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public String getJdbcdriver() {
		return jdbcdriver;
	}
	public void setJdbcdriver(String jdbcdriver) {
		this.jdbcdriver = jdbcdriver;
	}
	public String getDburl() {
		return dburl;
	}
	public void setDburl(String dburl) {
		this.dburl = dburl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
