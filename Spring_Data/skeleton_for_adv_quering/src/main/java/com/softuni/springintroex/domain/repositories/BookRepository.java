package com.softuni.springintroex.domain.repositories;

import com.softuni.springintroex.domain.entities.AgeRestriction;
import com.softuni.springintroex.domain.entities.Book;
import com.softuni.springintroex.domain.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Set<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    Set<Book> findAllByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal lower, BigDecimal higher);

    @Query("SELECT b from Book as b WHERE substring(b.releaseDate,0,4) not like :year")
    Set<Book> findAllByReleaseDateIsNot(String year);

    Set<Book> findAllByReleaseDateIsLessThan(LocalDate date);

    Set<Book> findAllByTitleContaining(String string);

    @Query("SELECT COUNT(b) FROM Book as b where LENGTH(b.title) > :length")
    int getNumberOfBooksWithTitleLength(int length);

    Book findByTitle(String title);

    @Transactional
    @Modifying
    @Query("update Book b set b.copies = b.copies + :copies WHERE b.releaseDate >= :date")
    int updateCopies(int copies, LocalDate date);
}
