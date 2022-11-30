package com.softuni.springintroex.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface AuthorService {
    void seedAuthorsInDB() throws IOException;

    void printAllAuthorsWhoseFirstNameEndsWith(String suffix);

    void printAllBooksOfAuthorsWithLastNameStartingWith(String string);

    void printAllAuthorByBookCopies();
}
