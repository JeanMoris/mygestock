package com.mystock.mygestock.service.Impl;

import com.mystock.mygestock.dto.CategoryDto;
import com.mystock.mygestock.exception.EntityNotFoundException;
import com.mystock.mygestock.exception.ErrorCodes;
import com.mystock.mygestock.exception.InvalidEntityException;
import com.mystock.mygestock.model.Category;
import com.mystock.mygestock.repository.CategoryRepository;
import com.mystock.mygestock.service.CategoryService;
import com.mystock.mygestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Category is not valid !{}", dto);
            throw new InvalidEntityException("Catégorie non valide !!!",ErrorCodes.CATEGORIE_NOT_VALID);
        }
        return CategoryDto.fromEntity(
               categoryRepository.save(
                        CategoryDto.toEntity(dto))
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        if (id == null){
            log.error("Category ID is null");
            return null;
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun categorie avec l'ID = " + id + " n'a été trouvé dans la base",
                        ErrorCodes.CATEGORIE_NOT_FOUND));
        return CategoryDto.fromEntity(category);
    }

    @Override
    public void delete(Long id) {
        Category categorie = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun categorie avec l'ID = " + id + " n'a été trouvé dans la base",
                        ErrorCodes.CATEGORIE_NOT_FOUND));
        categoryRepository.delete(categorie);
    }
}
