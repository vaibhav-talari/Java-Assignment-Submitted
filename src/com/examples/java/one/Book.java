
package com.examples.java.one;
//import java.util.Scanner;
import java.time.LocalDate;

public class Book implements java.io.Serializable {

	private static final long serialVersionUID = -342534524065096694L;
	public long bookid;
	public String title;
	public double price;
	public int volume;
	LocalDate publishDate;  
	public int year,day,month;
	
	
	public Book()
	{
		
	}
	
	public Book(long bookid,String title,double price,int volume,int year,int month,int day)
	{
		this.bookid=bookid;
		this.title=title;
		this.price=price;
		this.volume=volume;	
		this.day=day;
		this.month=month;
		this.year=year;
		publishDate = LocalDate.of(year, month, day);

	}
	
	public void Setbookid(long bookid)
	{
		this.bookid=bookid;
	}
	public void Setbooktitle(String title)
	{
		this.title=title;
	}
	public void Setbookprice(double price)
	{
		this.price=price;
	}
	public void Setbookvolume(int volume)
	{
		this.volume=volume;	
	}
	
	public long getBookid()
	{
		return bookid;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public int getVolume()
	{
		return volume;
	}
	
	public void setpublishedDate(int year,int month,int day)
	{
		this.day=day;
		this.month=month;
		this.year=year;
		publishDate = LocalDate.of(year, month, day);
	}
	
	public LocalDate getpublishedDate()
	{
		return publishDate;
	}
	
	public int getbookyear()
	{
		return year;
	}
	public int getbookmonth()
	{
		return month;
	}
	public int getbookdate()
	{
		return day;
	}
	
	}
	

