package com.lms.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lms.models.Book;
import com.lms.lms.services.LmsService;
import com.lms.lms.exceptions.ResourceNotFoundException;


@RestController
@RequestMapping("api/books")
public class lmsController {
	
	@Autowired
	private LmsService lmsService;
	
	@GetMapping
	public List<Book> getAllBooks() {
		return lmsService.getAllBooks();
	}
	
	@GetMapping("{id}")
	public Book getBookById(@PathVariable int id) {
		return lmsService.getBookById(id);
	}
	@PostMapping()
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book newBook=lmsService.addBook(book);
		return new ResponseEntity<Book>(newBook,HttpStatus.CREATED);
	}
	@PutMapping("{id}/borrow")
	public ResponseEntity<String> borrowBook(@PathVariable int id) {
		return new ResponseEntity<String> (lmsService.borrowBook(id),HttpStatus.OK);
		
	}
	@PutMapping("{id}/return")
	public ResponseEntity<Boolean> returnBook(@PathVariable int id) {
		return new ResponseEntity<Boolean> (lmsService.returnBook(id),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id){
		boolean result=lmsService.deleteBook(id);
		if(!result) {
			throw new ResourceNotFoundException("books", "id",id);
		}
		 
		return new ResponseEntity<String>("Successfully Deleted Book",HttpStatus.OK);
	
	}
}