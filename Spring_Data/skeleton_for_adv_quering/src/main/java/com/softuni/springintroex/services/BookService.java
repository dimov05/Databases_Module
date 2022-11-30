package com.softuni.springintroex.services;

import com.softuni.springintroex.domain.entities.Book;
import com.softuni.springintroex.domain.entities.EditionType;
import com.softuni.springintroex.services.models.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface BookService {
    void seedBooksInDB() throws IOException;

    void printAllBooksByAgeRestriction(String ageRes);

    void printAllBooksByEditionTypeWithCopiesLessThan(EditionType editionType, int copies);

    void printAllBooksWithPriceLowerThanOrPriceHigherThan(BigDecimal lower, BigDecimal higher);

    void printAllBooksByBookNotInYear(String year);

    void printAllBooksByReleaseDateBefore(String date);

    void printAllBooksByTitleContainingString(String string);

    void printCountOfBooksWithTitleLongerThan(int length);

    BookInfo findBookByTitle(String title);

    void printUpdatedCopiesCount(String date, int copies);

}
