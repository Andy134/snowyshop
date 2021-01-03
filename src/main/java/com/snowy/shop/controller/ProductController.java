package com.snowy.shop.controller;

import com.snowy.shop.constants.ProductStatus;
import com.snowy.shop.model.ProductDto;
import com.snowy.shop.model.ProductInsertDto;
import com.snowy.shop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getActiveProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAllByStatus(ProductStatus.ACTIVE, pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody ProductInsertDto dto) {
        productService.save(dto);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateStatus(@PathVariable Long id, @RequestBody ProductStatus status) {
        productService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

}
