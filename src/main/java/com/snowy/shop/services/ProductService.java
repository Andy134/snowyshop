package com.snowy.shop.services;

import com.snowy.shop.constants.ProductStatus;
import com.snowy.shop.model.ProductDto;
import com.snowy.shop.model.ProductInsertDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDto> findAllByStatus(ProductStatus pageStatus, Pageable pageable);

    void save(ProductInsertDto productDto);

    void updateStatus(Long id, ProductStatus status);

    void delete(Long id);

    ProductDto findById(Long id);
}
