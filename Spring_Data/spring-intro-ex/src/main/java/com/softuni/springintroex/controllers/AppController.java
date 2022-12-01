package com.softuni.springintroex.controllers;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        // Exercise 1
        //List<Book> booksAfter2000 = this.bookService.getAllBooksAfter2000();

        // Exercise 2
        for (Author author : this.authorService.findAllAuthorsWithBooksBefore()) {
            System.out.printf("%s %s%n",
                    author.getFirstName(), author.getLastName());
        }
        // Exercise 3
        /*this.authorService.findAllAuthorsByCountOfBooks()
                .forEach(a -> {
                    System.out.printf("%s %s %d%n",
                            a.getFirstName(), a.getLastName(), a.getBooks().size());
                });*/

    }
}
