package com.snowy.shop.services;

import com.snowy.shop.model.CategoryDto;
import com.snowy.shop.model.CategoryInsertDto;
import com.snowy.shop.model.ProductInsertDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> findAll(Pageable pageable);

    void save(CategoryInsertDto categoryDto);

    void delete(Long id);

    CategoryDto findById(Long id);
}
