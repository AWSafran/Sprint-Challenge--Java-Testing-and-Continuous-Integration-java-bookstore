package com.lambdaschool.bookstore.service;

import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks()
    {
        List<Book> rtnList = new ArrayList<>();
        
        bookRepository.findAll().iterator().forEachRemaining(rtnList::add);
        
        return rtnList;
    }
    
    @Transactional
    @Override
    public Book updateBook(long id, Book book) throws EntityNotFoundException
    {
        if(bookRepository.findById(id).isPresent())
        {
            Book existingBook = bookRepository.findByBookid(id);

            if(book.getBooktitle() == null)
            {
                book.setBooktitle(existingBook.getBooktitle());
            }
            if(book.getIsbn() == null)
            {
                book.setIsbn(existingBook.getIsbn());
            }
            if(book.getCopy() == 0) //int will be zero instead of null if not initialized
            {
                book.setCopy(existingBook.getCopy());
            }
            
            bookRepository.updateBook(id, book.getBooktitle(), book.getIsbn(), book.getCopy());
        }else
        {
            throw new EntityNotFoundException();
        }
        
        return book;
        
    }
}
