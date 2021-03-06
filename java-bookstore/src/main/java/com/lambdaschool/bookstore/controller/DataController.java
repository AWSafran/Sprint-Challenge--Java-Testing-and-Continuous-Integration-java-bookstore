package com.lambdaschool.bookstore.controller;

import com.lambdaschool.bookstore.exception.ResourceNotFoundException;
import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.service.AuthorService;
import com.lambdaschool.bookstore.service.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping
public class DataController
{
    @Autowired
    private BookService bookService;
    
    @Autowired
    private AuthorService authorService;
    
    @ApiOperation(value = "Return a list of all books", response = Book.class, responseContainer = "list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Select page to retrieve"),
            @ApiImplicitParam(name = "size", dataType = "Integer", paramType = "query", value = "Determine number of results per page"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> getAllBooks(@PageableDefault(page = 0, size = 5)Pageable pageable)
    {
        
        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Return a list of all authors", response = Book.class, responseContainer = "list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Select page to retrieve"),
            @ApiImplicitParam(name = "size", dataType = "Integer", paramType = "query", value = "Determine number of results per page"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> getAllAuthors(@PageableDefault(page = 0, size = 5)Pageable pageable)
    {
        return new ResponseEntity<>(authorService.findAllAuthors(pageable), HttpStatus.OK);
    }
    
    
    @ApiOperation(value = "Updates data for a given book, excluding author", notes = "The updated book will be returned", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book updated successfully", response = Book.class),
            @ApiResponse(code = 404, message = "Book to update not found in system", response = ResourceNotFoundException.class)
    })
    @PutMapping(value = "/data/books/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book updatedBook)
    {
        return new ResponseEntity<>(bookService.updateBook(id, updatedBook), HttpStatus.OK);
    }
    
    @ApiOperation(value = "assigns a book to the specified author", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully assigned book to author", response = void.class),
            @ApiResponse(code = 404, message = "Selected book or author could not be found", response = ResourceNotFoundException.class)
    })
    @PostMapping(value = "/data/books/authors/{id}")
    public ResponseEntity<?> assignBookToAuthor(@PathVariable long id, @RequestBody long bookId)
    {
        authorService.assignBookToAuthor(bookId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation(value = "Deletes specified book from records", notes = "This will also delete the book from all authors it was assigned to, but will not delete authors", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted book", response = void.class),
            @ApiResponse(code = 404, message = "Could not find selected book to delete", response = ResourceNotFoundException.class)
    })
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
