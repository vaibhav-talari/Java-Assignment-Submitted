package com.examples.java.one;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.example.java.DAO.DatabaseDAO;



public class libraryService {
	
public libraryService()
{
	
}
	//ArrayList<Subject> subjects = new ArrayList<Subject>();
	//Map<Long, Subject> subjects = new HashMap<>();
	//Set<Book> references=new HashSet<Book>();
	//Map<Long,Book> references=new HashMap<>();
	
			//DatabaseDAO databaseconnect = new DatabaseDAO();
			DatabaseDAO databaseconnect;

			public libraryService(DatabaseDAO databaseconnect)
			{
				this.databaseconnect=databaseconnect;
			}
	/*public Subject getsubject(long id) 
	{
		Subject subs = null;
		for (Object item : subjects) {
			subs = (Subject) item;
			if (subs.getSubjectid() == id) {
				return subs;
			}
		}
		return null;
	}*/
	
				public Subject getsubject(long id)
				{
					//return subjects.get(id);
					return databaseconnect.getSubject(id);
				}
	
	/*public Book getbook(long id) 
	{
		Book bks = null;
		for (Object item : references) {
			bks = (Book) item;
			if (bks.getBookid() == id) {
				return bks;
			}
		}
		return null;
	}*/
				
				public Book getbook(long id)
				{
					//return references.get(id);
					return databaseconnect.getBook(id);
				}
	
	
	/*public boolean addsubject(Subject subs)
	{
		return subjects.add(subs);
	}*/
	
			public boolean addsubject(Subject subs)
			{
				//return subjects.put(subs.getSubjectid(),subs) != null ? false : true;
				return databaseconnect.createSubject(subs);
			}
	
	/*public boolean addbook(Book bks) 
	{
		return references.add(bks);
	}*/
			
			public boolean addbook(Book bks)
			{
				//return references.put(bks.getBookid(),bks) != null ? false :true ;
				return databaseconnect.createBook(bks);
				
			}
			
	/*public boolean deletesubject(Subject subs)
	{
		return subjects.remove(subs);
	}*/
	
				public boolean deletesubject(Subject subs) 
				{
					//return subjects.remove(subs.getSubjectid()) != null ? true : false;
					return databaseconnect.deleteSubject(subs.getSubjectid());
				}
	
	/*public boolean deletebook(Book bks)
	{
		return references.remove(bks);
	}*/
				
				public boolean deletebook(Book bks) 
				{
					//return references.remove(bks.getBookid()) != null ? true : false;
					return databaseconnect.deleteBook(bks.getBookid());
				}	
				
	/*public Set<Book> getAllbooks() 
	{
		return references;
	}*/
	
				public List<Book> getAllbooks() 
				{
					//return new ArrayList<>(references.values());
					return databaseconnect.getAllBooks();
				}
	
	/*public ArrayList<Subject> getAllsubjects() 
	{
		return subjects;
	}*/
	
	
			public List<Subject> getAllsubjects() 
			{
				//return new ArrayList<>(subjects.values());
				return databaseconnect.getAllSubject();
			}
			public List<Book> sortbookbytitle()
			{
				List<Book> sort=new ArrayList<>();
				sort=databaseconnect.getAllBooks();
				Map<Long,Book> map=new HashMap<>();
				for(Book run:sort)
				{
					map.put(run.getBookid(),run);
				}
				return new ArrayList<>(map.values().stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList()));
			}
			public List<Subject> sortsubjectbytitle()
			{
				{
					List<Subject> sort=new ArrayList<>();
					sort=databaseconnect.getAllSubject();
					Map<Long,Subject> map=new HashMap<>();
					for(Subject run:sort)
					{
						map.put(run.getSubjectid(),run);
					}
					return new ArrayList<>(map.values().stream().sorted(Comparator.comparing(Subject::getSubjecttitle)).collect(Collectors.toList()));
				}
			}
			public List<Book> sortbookbydate()
			{
				List<Book> sort=new ArrayList<>();
				sort=databaseconnect.getAllBooks();
				Map<Long,Book> map=new HashMap<>();
				for(Book run:sort)
				{
					map.put(run.getBookid(),run);
				}
				return new ArrayList<>(map.values().stream().sorted(Comparator.comparing(Book::getpublishedDate)).collect(Collectors.toList()));
			}
	

}
	
