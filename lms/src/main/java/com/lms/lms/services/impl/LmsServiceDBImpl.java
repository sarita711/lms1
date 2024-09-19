package com.lms.lms.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.lms.models.Book;
import com.lms.lms.repositories.BooksRepository;
import com.lms.lms.services.LmsService;
import com.lms.lms.exceptions.ResourceNotFoundException;

@Service
public class LmsServiceDBImpl implements LmsService {
	
	@Autowired
	public BooksRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
		}

	@Override
	public Book getBookById(int id) {
		Optional<Book> book=bookRepository.findById(id);
		if(book.isPresent())
			return book.get();
		throw new ResourceNotFoundException("books", "id", id);
	}

	@Override
	public Book addBook(Book newBook) {
		return bookRepository.save(newBook);
	}

	@Override
	public String borrowBook(int id) {
		Book toBorrow=bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("books", "id", id));
		
		if(!toBorrow.isBorrowed()) {
			toBorrow.setBorrowed(true);
			bookRepository.save(toBorrow);
			return "Successfully Borrowed";
		}
		else {
			return "Book is already borrowed";
		}
	}

	@Override
	public boolean returnBook(int id) {
		Book toReturn=bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("books", "id", id));
		
		toReturn.setBorrowed(false);
		bookRepository.save(toReturn);
		return true;
	}

	@Override
	public boolean deleteBook(int id) {
		bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("books", "id", id));
		bookRepository.deleteById(id);
		return true;
	}

}
