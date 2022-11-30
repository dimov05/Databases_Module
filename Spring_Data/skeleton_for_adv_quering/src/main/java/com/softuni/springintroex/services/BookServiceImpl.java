package com.softuni.springintroex.services;

import com.softuni.springintroex.domain.entities.*;
import com.softuni.springintroex.domain.repositories.AuthorRepository;
import com.softuni.springintroex.domain.repositories.BookRepository;
import com.softuni.springintroex.domain.repositories.CategoryRepository;
import com.softuni.springintroex.services.models.BookInfo;
import com.softuni.springintroex.utils.FileUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static com.softuni.springintroex.constants.GlobalConstants.*;

@Service
public class BookServiceImpl implements BookService {
    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public void seedBooksInDB() throws IOException {
        String[] fileContent = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        Random random = new Random();
        for (String line : fileContent) {
            String[] tokens = line.split("\\s+");

            long authorIndex = random.nextInt((int) this.authorRepository.count()) + 1;
            Author author = this.authorRepository.findById(authorIndex).get();
            EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(tokens[1], formatter);
            int copies = Integer.parseInt(tokens[2]);
            BigDecimal price = new BigDecimal(tokens[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < tokens.length; i++) {
                titleBuilder.append(tokens[i]).append(" ");
            }

            String title = titleBuilder.toString().trim();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(localDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(this.getRandomCategories());

            this.bookRepository.saveAndFlush(book);
        }
    }


    @Override
    public void printAllBooksByAgeRestriction(String ageRes) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRes.toUpperCase());
        this.bookRepository.findAllByAgeRestriction(ageRestriction)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllBooksByEditionTypeWithCopiesLessThan(EditionType editionType, int copies) {
        this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllBooksWithPriceLowerThanOrPriceHigherThan(BigDecimal lower, BigDecimal higher) {
        this.bookRepository.findAllByPriceIsLessThanOrPriceIsGreaterThan(lower, higher)
                .forEach(book -> System.out.println(book.getTitle() + " - $" + book.getPrice()));
    }

    @Override
    public void printAllBooksByBookNotInYear(String year) {
        this.bookRepository.findAllByReleaseDateIsNot(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllBooksByReleaseDateBefore(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, dtf);

        this.bookRepository.findAllByReleaseDateIsLessThan(localDate)
                .forEach(book -> System.out.printf("%s %s %s%n",
                        book.getTitle(), book.getEditionType().name(), book.getPrice()));
    }

    @Override
    public void printAllBooksByTitleContainingString(String string) {
        this.bookRepository.findAllByTitleContaining(string)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printCountOfBooksWithTitleLongerThan(int length) {
        System.out.println(this.bookRepository.getNumberOfBooksWithTitleLength(length));
    }

    @Override
    public BookInfo findBookByTitle(String title) {
        Book book = this.bookRepository.findByTitle(title);
        BookInfo bookInfo = new BookInfo(book.getTitle(), book.getPrice(), book.getCopies());
        return bookInfo;
    }

    @Override
    public void printUpdatedCopiesCount(String date, int copies) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, dtf);
        int updatedRows = this.bookRepository.updateCopies(copies, localDate);
        System.out.println(updatedRows * copies);
    }


    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            long categoryIndex = random.nextInt((int) this.categoryRepository.count()) + 1;
            Category category = this.categoryRepository.findById(categoryIndex).get();
            categories.add(category);
        }

        return categories;
    }
}
