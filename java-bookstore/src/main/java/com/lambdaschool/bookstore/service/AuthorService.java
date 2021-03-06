package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService
{
    List<Author> findAllAuthors(Pageable pageable);
    
    void assignBookToAuthor(long bookid, long authorid);
}
