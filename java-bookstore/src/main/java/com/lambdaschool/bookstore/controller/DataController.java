package com.lambdaschool.bookstore.controller;

import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.service.AuthorService;
import com.lambdaschool.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DataController
{
    @Autowired
    private BookService bookService;
    
    @Autowired
    private AuthorService authorService;
    
    @GetMapping(value = "/books")
    public ResponseEntity<?> getAllBooks(@PageableDefault(page = 0, size = 5)Pageable pageable)
    {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/authors")
    public ResponseEntity<?> getAllAuthors(@PageableDefault(page = 0, size = 5)Pageable pageable)
    {
        return new ResponseEntity<>(authorService.findAllAuthors(), HttpStatus.OK);
    }
    
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book updatedBook)
    {
        return new ResponseEntity<>(bookService.updateBook(id, updatedBook), HttpStatus.OK);
    }
    
    @PostMapping(value = "/data/books/authors/{id}")
    public ResponseEntity<?> assignBookToAuthor(@PathVariable long id, @RequestBody long bookId)
    {
        authorService.assignBookToAuthor(bookId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
