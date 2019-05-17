package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Book;

import java.util.List;

public interface BookService
{
    List<Book> getAllBooks();
    
    Book updateBook(long id, Book book);
    
    void deleteBook(long id);
}
