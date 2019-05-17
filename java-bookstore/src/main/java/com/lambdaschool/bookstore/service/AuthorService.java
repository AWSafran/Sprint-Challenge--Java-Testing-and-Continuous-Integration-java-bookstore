package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Author;

import java.util.List;

public interface AuthorService
{
    List<Author> findAllAuthors();
    
    void assignBookToAuthor(long bookid, long authorid);
}
