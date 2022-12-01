package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.softuni.springintroex.constants.GlobalConstants.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author = this.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1], dateTimeFormatter);

                    int copies = Integer.parseInt(params[2]);

                    BigDecimal price = new BigDecimal(params[3]);

                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];

                    String title = this.getTitle(params);

                    Set<Category> categories = this.getRandomCategories();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setCategories(categories);
                    book.setTitle(title);
                    book.setAgeRestriction(ageRestriction);

                    this.bookRepository.saveAndFlush(book);
                });
    }

    @Override
    public List<Book> getAllBooksAfter2000() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate releaseDate = LocalDate.parse("31/12/2000", dateTimeFormatter);
        return this.bookRepository.findAllByReleaseDateAfter(releaseDate);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;


        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById((long) categoryId));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 5; i < params.length; i++) {
            sb.append(params[i])
                    .append(" ");
        }
        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }
}
