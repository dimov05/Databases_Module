package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    int getAuthorsCount();
    Author findAuthorById(Long id);

    List<Author> findAllAuthorsByCountOfBooks();

    List<Author> findAllAuthorsWithBooksBefore();
}
