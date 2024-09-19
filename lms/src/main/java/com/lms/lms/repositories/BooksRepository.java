package com.lms.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.lms.models.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {

}
