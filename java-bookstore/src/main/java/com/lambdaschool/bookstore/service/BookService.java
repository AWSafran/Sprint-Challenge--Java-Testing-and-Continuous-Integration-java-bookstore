package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
    List<Book> getAllBooks(Pageable pageable);
    
    Book updateBook(long id, Book book);
    
    void deleteBook(long id);
}
