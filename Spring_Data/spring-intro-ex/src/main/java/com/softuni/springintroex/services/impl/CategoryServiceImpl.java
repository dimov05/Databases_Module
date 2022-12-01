package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static com.softuni.springintroex.constants.GlobalConstants.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }
        String[] fileContent = this.fileUtil
                .readFileContent(CATEGORIES_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r -> {
                    Category category = new Category(r);
                    this.categoryRepository.saveAndFlush(category);
                });

    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.getOne(categoryId);
    }
}
