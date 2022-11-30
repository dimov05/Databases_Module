package com.softuni.springintroex;

import com.softuni.springintroex.domain.entities.EditionType;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.services.models.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class Engine implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public Engine(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        //Ex. 1
        //this.bookService.printAllBooksByAgeRestriction("MiNoR");

        //Ex. 2
        //this.bookService.printAllBooksByEditionTypeWithCopiesLessThan(EditionType.GOLD,5000);

        //Ex. 3
        //this.bookService
        //        .printAllBooksWithPriceLowerThanOrPriceHigherThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));

        //Ex. 4
        //this.bookService.printAllBooksByBookNotInYear("2000");

        //Ex. 5
        //this.bookService.printAllBooksByReleaseDateBefore("14-04-1992");

        //Ex. 6
        //this.authorService.printAllAuthorsWhoseFirstNameEndsWith("dy");

        //Ex. 7
        //this.bookService.printAllBooksByTitleContainingString("WOR");

        //Ex. 8
        //this.authorService.printAllBooksOfAuthorsWithLastNameStartingWith("gr");

        //Ex. 9
        //this.bookService.printCountOfBooksWithTitleLongerThan(40);

        //Ex. 10
        //this.authorService.printAllAuthorByBookCopies();

        //Ex. 11
        //this.bookService.printBookInformationByTitle("Things Fall Apart");
        //BookInfo bookInfo = this.bookService.findBookByTitle("Things Fall Apart");
        //System.out.printf("%s %s %d%n",
        //        bookInfo.getTitle(), bookInfo.getPrice(), bookInfo.getCopies());

        //Ex. 12
        //this.bookService.printUpdatedCopiesCount("12-10-2005",100);

    }

    void seedData() throws IOException {
        this.categoryService.seedCategoriesInDB();
        this.authorService.seedAuthorsInDB();
        this.bookService.seedBooksInDB();
    }
}
