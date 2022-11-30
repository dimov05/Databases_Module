package com.softuni.springintroex.domain.repositories;

import com.softuni.springintroex.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
        Set<Author> findAllByFirstNameEndingWith(String suffix);

        Set<Author> findAllByLastNameStartingWith(String string);


}
