package com.examples.java.one;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Set;

public class DeserializeFile {
	@SuppressWarnings("unchecked")
	public static void main(String [] args) {
	      ArrayList<Subject> e = null;
	      Set<Book> f=null;
	      try {
	         FileInputStream fileIn = new FileInputStream("C:\\Users\\715328\\Desktop\\programcodeoutput\\libraryservice.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (ArrayList<Subject>) in.readObject();
	         f=(Set<Book>)in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Subject class not found");
	         c.printStackTrace();
	         return;
	      }
    	  System.out.println("Deserialized Subject...");

	      for (Subject run : e) 
	    	{
		      System.out.println("ID:" + run.subjectid);
		      System.out.println("Title:" + run.subtitle);
		      System.out.println("Duration:" + run.duration_in_hours);
	    	}
    	  System.out.println("Deserialized Book...");

	      for (Book run : f) 
	    	{
		      System.out.println("ID:" + run.getBookid());
		      System.out.println("Title:" + run.getTitle());
		      System.out.println("Price:" + run.getPrice());
		      System.out.println("Volume:" + run.getVolume());
		      System.out.println("Time:" + run.getpublishedDate());

	    	}
	      
	   }

}
