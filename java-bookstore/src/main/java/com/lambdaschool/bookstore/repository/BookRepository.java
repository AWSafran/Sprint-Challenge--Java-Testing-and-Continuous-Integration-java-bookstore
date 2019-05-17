package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>
{
    Book findByBookid(long id);
    
    @Modifying
    @Query(value = "UPDATE books SET booktitle=:bookTitle, isbn=:isbn, copy=:copy WHERE bookid=:bookid", nativeQuery = true)
    void updateBook(long bookid, String bookTitle, String isbn, int copy);
    
    @Modifying
    @Query(value = "DELETE FROM bookauthors WHERE bookid=:bookid", nativeQuery = true)
    void deleteBookFromAuthorList(long bookid);
}
