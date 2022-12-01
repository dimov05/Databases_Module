package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> getAllBooksAfter2000();
}
