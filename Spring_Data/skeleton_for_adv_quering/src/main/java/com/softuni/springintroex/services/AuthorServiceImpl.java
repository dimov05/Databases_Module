package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.domain.entities.Author;
import com.softuni.springintroex.domain.entities.Book;
import com.softuni.springintroex.domain.repositories.AuthorRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.softuni.springintroex.constants.GlobalConstants.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthorsInDB() throws IOException {
        String[] lines = this.fileUtil.readFileContent(AUTHORS_FILE_PATH);

        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            Author author = new Author(tokens[0], tokens[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public void printAllAuthorsWhoseFirstNameEndsWith(String suffix) {
        this.authorRepository.findAllByFirstNameEndingWith(suffix)
                .forEach(a -> System.out.printf("%s %s %n",
                        a.getFirstName(), a.getLastName()));
    }

    @Override
    public void printAllBooksOfAuthorsWithLastNameStartingWith(String string) {
        this.authorRepository.findAllByLastNameStartingWith(string)
                .forEach(author -> {
                    for (Book book : author.getBooks()) {
                        System.out.printf("%s (%s %s)%n",
                                book.getTitle(), author.getFirstName(), author.getLastName());
                    }
                });
    }

    @Override
    public void printAllAuthorByBookCopies() {
        List<Author> authors = this.authorRepository.findAll();
        Map<String, Integer> authorCopies = new HashMap<>();
        authors.forEach(author -> {
            int copiesOfAuthor = author.getBooks().stream().mapToInt(Book::getCopies).sum();
            authorCopies.put(author.getFirstName() + " " + author.getLastName(), copiesOfAuthor);
        });
        authorCopies
                .entrySet()
                .stream().sorted((current, next) -> Integer.compare(next.getValue(), current.getValue()))
                .forEach(author -> System.out.printf("%s %d%n",
                        author.getKey(), author.getValue()));

        System.out.println();
    }
}
