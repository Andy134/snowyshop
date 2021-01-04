package com.snowy.shop.services.impl;

import com.snowy.shop.entity.Category;
import com.snowy.shop.error.BusinessException;
import com.snowy.shop.error.Errors;
import com.snowy.shop.model.CategoryDto;
import com.snowy.shop.model.CategoryInsertDto;
import com.snowy.shop.repository.CategoryRepository;
import com.snowy.shop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    ModelMapper modelMapper = new ModelMapper();

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        List<CategoryDto> dtos = new ArrayList<>();
        Page<Category> entities = categoryRepository.findAll(pageable);
        entities.forEach(entity -> {
            dtos.add(modelMapper.map(entity, CategoryDto.class));
        });
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    @Override
    public void save(CategoryInsertDto dto) {
        Category entity = modelMapper.map(dto, Category.class);
        categoryRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Category entity = categoryRepository.findById(id).orElseThrow(()->new BusinessException(Errors.DATA_NOT_FOUND));
        categoryRepository.delete(entity);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new BusinessException(Errors.DATA_NOT_FOUND));
        return modelMapper.map(category, CategoryDto.class);
    }
}
