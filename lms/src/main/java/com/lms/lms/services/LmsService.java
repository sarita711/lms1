package com.lms.lms.services;

import java.util.List;

import com.lms.lms.models.Book;

public interface LmsService {

	List<Book> getAllBooks();
	
	Book getBookById(int id);
	
	Book addBook(Book newBook);
	
	String borrowBook(int id);
	
	boolean returnBook(int id);
	
	boolean deleteBook(int id);
	
}
