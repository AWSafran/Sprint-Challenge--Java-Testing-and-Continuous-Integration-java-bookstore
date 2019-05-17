package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.model.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long>
{
    List<Author> findAll(Pageable pageable);
    
    @Modifying
    @Query(value = "INSERT INTO bookauthors(bookid, authorid) VALUES(:bookid, :authorid)", nativeQuery = true)
    public void assignAuthorToBook(long bookid, long authorid);
}
