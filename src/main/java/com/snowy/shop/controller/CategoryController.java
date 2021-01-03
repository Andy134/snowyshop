package com.snowy.shop.controller;

import com.snowy.shop.model.CategoryDto;
import com.snowy.shop.model.CategoryInsertDto;
import com.snowy.shop.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<CategoryDto> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody CategoryInsertDto dto) {
        categoryService.save(dto);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
