package com.lms.lms.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.lms.exceptions.ResourceNotFoundException;
import com.lms.lms.models.Book;
import com.lms.lms.services.LmsService;


public class lmsServiceImpl implements LmsService {

	private static List<Book> books=new ArrayList<>();
	private static int booksCount=1;
	
	
	static {
		books.add(new Book(1,"The Pearls","Ronald","B001",false));
	}
	@Override
	public List<Book> getAllBooks() {
		return books;
	}

	@Override
	public Book getBookById(int id) {
		for(Book book:books) {
			if(book.getId()==id) {
				return book;
			}
		}
		throw new ResourceNotFoundException("books", "id", id);
	}

	@Override
	public Book addBook(Book newBook) {
		booksCount++;
		newBook.setId(booksCount);
		books.add(newBook);
		return newBook;
	}

	@Override
	public String borrowBook(int id) {
		for(Book book:books) {
			if(book.getId()==id) {
				if(!book.isBorrowed()) {
					book.setBorrowed(true);
					return "Successfully Borrowed";
				}
				else {
					return "Book is already borrowed";
				}
			}
		}
		throw new ResourceNotFoundException("books", "id",id); 
	}

	@Override
	public boolean returnBook(int id) {
		 for(Book book:books) {
			 if(book.getId()==id)
			 book.setBorrowed(false);
		 }
		 return true;
	}

	@Override
	public boolean deleteBook(int id) {
		for(Book book:books) {
			if(book.getId()==id) {
				books.remove(id);
				return true;
			}
		}
		return false;
	}

}
