package com.examples.java.one;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.example.java.connect.DataSourceConnect;
import com.examples.java.exception.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




public class SuperMain 
{

	private static Scanner scanner = null;
	private static libraryService libservice = null;
	private static AbstractApplicationContext context=null;
	private static DataSourceConnect datasourceconnect=null;
	
	public static void main(String[] args) {
		int option;
		
		context = new ClassPathXmlApplicationContext("configuration-file.xml");

		scanner = new Scanner(System.in);
		//libservice = new libraryService();
		
		libservice=(libraryService)context.getBean("liberaryservice");
		datasourceconnect=(DataSourceConnect)context.getBean("datasourceconnect");
		
		System.out.println("Welcome to the LIBRARY!!!");
		try
		{
		do{
				System.out.println();
				System.out.println("1. Add Subject");
				System.out.println("2. Add Book");
				System.out.println("3. Delete Subject");
				System.out.println("4. Delete Book");
				System.out.println("5. Search Subject");
				System.out.println("6. Search Book");
				System.out.println("7. List Books sorted by Title");
				System.out.println("8. List Subjects sorted by Title");
				System.out.println("9. List Books sorted by published date");
				System.out.println("10. Exit");
				System.out.print("\nEnter your option: ");
				option = scanner.nextInt();
					try{		
					switch (option) 
					{
					case 1:
						createSubject();
						break;
					case 2:
						createBook();
						break;
					case 3:
						deleteSubject();
						break;
					case 4:
						deleteBook();
						break;
					case 5:
						searchSubject();
						break;
					case 6:
						searchBook();
						break;
					case 7:
						sortBooksbytitle();
						break;
					case 8:
						sortSubjectbytitle();
						break;
					case 9:
						sortBooksbypublishdate();
						break;
					case 10:
						exit();
						break;
					default:
						System.out.println("Invalid option entered. Please enter correct option.");
						break;
					}
					}catch(exception1|exception2 e)
					{
						System.out.println(e.getMessage());
					}
				}while(option!=10);
	}finally{clean();}
	}
private static void createSubject() throws exception1
	{
	Subject subject = new Subject();

	System.out.println("\n-----Enter Subject details-----");
	System.out.print("\nEnter Subject ID: ");
	try{
		long id = Long.parseLong(scanner.next());
		scanner.nextLine();
		subject.SetsubjectID(id);
		}catch(InputMismatchException|NumberFormatException e)
			{
			throw new exception1("\n-+-+-+-\nInvalid Subject ID.\nPlease enter valid ID.\n-+-+-+-");
			}
		captureDetailSubject(subject);
		boolean isCreateSuccess = libservice.addsubject(subject);
		String message = isCreateSuccess ? "Subject created successfully." : "Subject creation failed.";
		System.out.println(message);
	 
}
private static void createBook()throws exception1
	{
	Book book = new Book();
	System.out.println("\nEnter Book details...");
	System.out.print("\nEnter Book ID: ");
	try{
	long id = Long.parseLong((scanner.next()));
	scanner.nextLine();
	book.Setbookid(id);
	}catch(NumberFormatException|InputMismatchException e)
		{
		throw new exception1("\n-+-+-+-\nInvalid Book ID.\nPlease enter valid ID.\n-+-+-+-");
		}
	captureDetailBook(book);
	boolean isCreateSuccess = libservice.addbook(book);
	String message = isCreateSuccess ? "Book created successfully." : "Book creation failed.";
	System.out.println(message);
	}
private static void deleteSubject() throws exception2
	{
	System.out.print("---Avaliable SUBJECTS---\n");
	listallsubjects();
	System.out.print("\nEnter ID of SUBJECT to delete:");
	long id = scanner.nextLong();
	Subject subject = libservice.getsubject(id);
	if(subject==null)
	{
		throw new exception2("\n-+-+-+-\nNo SUBJECT found with ID:"+id+"\n-+-+-+-");
	}
	boolean isDeleteSuccess = libservice.deletesubject(subject);
	String message = isDeleteSuccess ? "Subject deleted successfully." : "Subject deletion failed.";
	System.out.println(message);
	System.out.print("---SUBJECTS after deletion\n---");
	listallsubjects();
	}
private static void deleteBook() throws exception2
	{
	System.out.print("---Avaliable BOOKS---\n");
	listallbooks();
	System.out.print("\nEnter ID of BOOK to delete:");
	long id = scanner.nextLong();
	Book book = libservice.getbook(id);
	if(book==null)
	{
		throw new exception2("\n-+-+-+-\nNo BOOK found with ID:"+id+"\n-+-+-+-");
	}
	boolean isDeleteSuccess = libservice.deletebook(book);
	String message = isDeleteSuccess ? "Book deleted successfully." : "Book deletion failed.";
	System.out.println(message);
	System.out.print("---BOOKS after deletion---\n");
	listallbooks();
	}
private static void searchSubject() throws exception2
	{
	System.out.print("\nEnter ID of SUBJECT to view:");
	long id = scanner.nextLong();
	Subject subject = libservice.getsubject(id);
	if(subject==null)
	{
		throw new exception2("\n-+-+-+-\nNo RECORD of SUBJECT found with ID:"+id+"\n-+-+-+-");
	}
	printHeadersubject();
	printDetailSubject(subject);
	System.out.println();
	}
private static void searchBook() throws exception2
	{
	System.out.print("\nEnter ID of BOOK to view: ");
	long id = scanner.nextLong();
	Book book = libservice.getbook(id);
	if(book==null)
	{
		throw new exception2("\n-+-+-+-\nNo RECORD of Book found with ID:"+id+"\n-+-+-+-");
	}
	printHeaderbook();
	printDetailBook(book);
	System.out.println();
	}
private static void captureDetailSubject(Subject subject)  
	{
	System.out.print("Enter SUBJECT Title:");
	subject.Setsubjecttitle(scanner.nextLine());
	System.out.print("Enter duration of SUBJECT:");
	int time = Integer.parseInt(scanner.next());
	subject.Settimeduration(time);
	}
private static void captureDetailBook(Book book) throws exception1
	{
	System.out.print("Enter Book title: ");
	book.Setbooktitle(scanner.nextLine());
	try
	{
	System.out.print("Enter Book price: ");
	int cost = Integer.parseInt(scanner.next());
	book.Setbookprice(cost);
	}catch(InputMismatchException|NumberFormatException e)
	{
		throw new exception1("\n-+-+-+-\nEnter Valid Price\n-+-+-+-");
	}
	try
	{
	System.out.print("Enter Book Volume: ");
	int vol=Integer.parseInt(scanner.next());
	book.Setbookvolume(vol);
	}catch(InputMismatchException|NumberFormatException e)
	{
		throw new exception1("\n-+-+-+-\nEnter Valid Volume\n-+-+-+-");
	}
	System.out.print("Enter Book Published Year:");
	int year=Integer.parseInt(scanner.next());
	System.out.print("Enter Book Published Month:");
	int month=Integer.parseInt(scanner.next());
	System.out.print("Enter Book Published Date:");
	int date=Integer.parseInt(scanner.next());
	book.setpublishedDate(year, month, date);
	}
private static void printHeadersubject()
	{
	System.out.format("\n%5s%10s%15s", "ID", "TITLE", "Time Duration");
	}
private static void printHeaderbook()
{
System.out.format("\n%5s %10s %15s %20s %30s", "ID", "TITLE", "Price","Volume","Published Time");
}
private static void printDetailSubject(Subject subject) {
	if (subject == null) {
		return;
	}

	System.out.format("\n%5d%10s%15d", subject.getSubjectid(), subject.getSubjecttitle(), subject.getTime());
}
private static void printDetailBook(Book book) 
	{
	if (book == null) {
		return;
	}

	//System.out.format("\n%5d %10s %15f %20d %25tA, %tB' %td", book.getBookid(), book.getTitle(), book.getPrice(),book.getVolume(),book.getpublishedDate(),book.getpublishedDate(),book.getpublishedDate());
	System.out.format("\n%5d %10s %15f %20d %25tB %2td %2tY", book.getBookid(), book.getTitle(), book.getPrice(),book.getVolume(),book.getpublishedDate(),book.getpublishedDate(),book.getpublishedDate());

	}
private static void listallsubjects() 
	{

	List<Subject> subject = libservice.getAllsubjects();


	printHeadersubject();

	for (Object run : subject)
	{
		printDetailSubject((Subject) run);
	}
	System.out.println();
	}
private static void listallbooks() 
	{

	List<Book> book = libservice.getAllbooks();

	printHeaderbook();

	for (Object run : book) 
	{
		printDetailBook((Book) run);
	}
	System.out.println();
	}
private static void sortBooksbytitle()
{
	listselectedBooks(libservice.sortbookbytitle());
}
private static void sortSubjectbytitle()
{
	listselectedSubjects(libservice.sortsubjectbytitle());
}
private static void sortBooksbypublishdate()
{
	listselectedBooks(libservice.sortbookbydate());
}
private static void listselectedSubjects(List<Subject> subs)
{
	printHeadersubject();
	for(Object run:subs)
	{
		printDetailSubject((Subject)run);
	}
	System.out.println();
}

private static void listselectedBooks(List<Book> bks)
{
	printHeaderbook();
	for(Object run:bks)
	{
		printDetailBook((Book)run);
	}
	System.out.println();
}
private static void clean()
{
	scanner.close();
	datasourceconnect.closeConnection();
	context.registerShutdownHook();
	context.close();
}

private static void exit() 
	{
	clean();
	System.out.println("\nThank you. Data has been Saved!!!");
	System.exit(0);
	}
}


