package com.snowy.shop.repository;

import com.snowy.shop.constants.ProductStatus;
import com.snowy.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByStatus(ProductStatus status, Pageable pageable);
}
