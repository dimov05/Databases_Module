package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    List<Author> findByAuthorByCountOfBooks();


@Query("SELECT a FROM Author as a join Book as b where b.releaseDate < ?1")
    List<Author> findAuthorsByBooksBefore(LocalDate localDate);
}
