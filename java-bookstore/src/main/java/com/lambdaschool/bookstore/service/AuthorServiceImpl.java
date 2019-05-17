package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Author;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public List<Author> findAllAuthors()
    {
        List<Author> list = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(list::add);
        
        return list;
        
    }
    
    @Transactional
    @Override
    public void assignBookToAuthor(long bookid, long authorid) throws EntityNotFoundException
    {
        if(bookRepository.findById(bookid).isPresent() && authorRepository.findById(authorid).isPresent())
        {
            System.out.println("Book id is " + bookid + " author id is " + authorid);
            authorRepository.assignAuthorToBook(bookid, authorid);
        }else
        {
            throw new EntityNotFoundException();
        }
    
    }
}
