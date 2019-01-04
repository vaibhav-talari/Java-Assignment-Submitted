package com.example.java.DAO;

import java.sql.Connection;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.examples.java.one.*;
import com.example.java.connect.*;

public class DatabaseDAO {
	
	DataSourceConnect  sourceconnect=null;
	Connection conn=null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public DatabaseDAO(DataSourceConnect sourceconnect)
	{
		this.sourceconnect=sourceconnect;
		try {
			conn=sourceconnect.getConnection();
		}catch(ClassNotFoundException|SQLException e) {
			System.out.println("Error occured while creating the connection:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public DatabaseDAO()
	{
		
	}
	
	public boolean createSubject(Subject subject)
	{	
		//INSERT INTO subject(id, title,duration) values(100,"Math",50 );

	String query="INSERT INTO subject(id, title,duration) values("+subject.getSubjectid()+",\""+subject.getSubjecttitle()+"\","+subject.getTime()+")";
	int status=0;
	System.out.println("The query statement is-"+query);
		try {
			stmt=conn.createStatement();
			status = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status>0?true:false;
	}
	
	public boolean createBook(Book book)
	{	

	String query="INSERT INTO book(id,title,price,volume,publishyear,publishmonth,publishdate) values("+book.getBookid()+",\""+book.getTitle()+"\","+book.getPrice()+","+book.getVolume()+","+book.getbookyear()+","+book.getbookmonth()+","+book.getbookdate()+")";
	int status=0;
	System.out.println("The query statement is-"+query);
		try {
			stmt=conn.createStatement();
			status = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status>0?true:false;
	}

	public boolean deleteSubject(Long subjectID)
	{
		String query = "DELETE FROM subject WHERE id = " + subjectID;
		System.out.println("Delete query: " + query);
		int count = 0;
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(query);//executeUpdate returns number of records updated 
			
//			conn.commit();
		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return count > 0 ? true : false;
	}
	
	public boolean deleteBook(Long bookID)
	{
		String query = "DELETE FROM book WHERE id = " + bookID;
		System.out.println("Delete query: " + query);
		int count = 0;
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(query);//executeUpdate returns number of records updated 
			
//			conn.commit();
		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return count > 0 ? true : false;
	}
	
	
	
	public Subject getSubject(Long id)
	{	
			Subject sub=null;	
		try {
			stmt=conn.createStatement();
			String query="SELECT * FROM subject WHERE id = "+id;
			rs=stmt.executeQuery(query);
			

			while(rs.next())
			{
				long ID=rs.getLong("id");
				String title=rs.getString("title");
				int time=rs.getInt("duration");
				sub = new Subject(ID,title,time);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		return sub;
	}
	
	public Book getBook(Long id)
	{	
			Book bks=null;		
		try {
			stmt=conn.createStatement();
			String query="SELECT * FROM book WHERE id = "+id;
			rs=stmt.executeQuery(query);
			

			while(rs.next())
			{
				long ID=rs.getLong("id");
				String title=rs.getString("title");
				double price=rs.getDouble("price");
				int volume=rs.getInt("volume");
				int year=rs.getInt("publishyear");
				int month=rs.getInt("publishmonth");
				int date=rs.getInt("publishdate");

				bks = new Book(ID,title,price,volume,year,month,date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		return bks;
	}
	
	public List<Subject> getAllSubject()
	{
		List<Subject> subjectref=new ArrayList<>();

		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM subject");
			
			while(rs.next())
			{
				Long ID=rs.getLong("id");
				String title=rs.getString("title");
				int time=rs.getInt("duration");
				subjectref.add(new Subject(ID,title,time));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectref;
	}
	
	
	public List<Book> getAllBooks()
	{
		List<Book> bookref=new ArrayList<>();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM book");
			
			while(rs.next())
			{
				long ID=rs.getLong("id");
				String title=rs.getString("title");
				double price=rs.getDouble("price");
				int volume=rs.getInt("volume");
				int year=rs.getInt("publishyear");
				int month=rs.getInt("publishmonth");
				int date=rs.getInt("publishdate");

				bookref.add(new Book(ID,title,price,volume,year,month,date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookref;
	}
	
	private void closeResources() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}			
		} catch (SQLException e) {
			System.out.println("Error occured while closing the Statement and ResultSet - " + e.getMessage());
			e.printStackTrace();
		}
	}
}
