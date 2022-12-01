package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static com.softuni.springintroex.constants.GlobalConstants.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(AUTHORS_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author = new Author(params[0], params[1]);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return this.authorRepository.getOne(authorId);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findByAuthorByCountOfBooks();
    }

    @Override
    public List<Author> findAllAuthorsWithBooksBefore() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("31/12/1990", dateTimeFormatter);
        return this.authorRepository.findAuthorsByBooksBefore(releaseDate);
    }

}
