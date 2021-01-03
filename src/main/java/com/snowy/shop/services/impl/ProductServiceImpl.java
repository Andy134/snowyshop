package com.snowy.shop.services.impl;

import com.snowy.shop.constants.ProductStatus;
import com.snowy.shop.entity.Category;
import com.snowy.shop.entity.Product;
import com.snowy.shop.error.BussinessException;
import com.snowy.shop.error.Errors;
import com.snowy.shop.model.ProductDto;
import com.snowy.shop.model.ProductInsertDto;
import com.snowy.shop.repository.CategoryRepository;
import com.snowy.shop.repository.ProductRepository;
import com.snowy.shop.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductDto> findAllByStatus(ProductStatus pageStatus, Pageable pageable) {
        List<ProductDto> dtos = new ArrayList<>();
        Page<Product> entities = productRepository.findAllByStatus(pageStatus, pageable);
        entities.forEach(entity -> {
            dtos.add(modelMapper.map(entity, ProductDto.class));
        });
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    @Override
    public void save(ProductInsertDto productDto) {
        Product productEntity = modelMapper.map(productDto, Product.class);
        Set<Category> lstCategory = Arrays.stream(productDto.getCategoryIds()).mapToObj(id -> categoryRepository.findById(id).orElseThrow(() -> new BussinessException(Errors.DATA_NOT_FOUND))).collect(Collectors.toSet());
        productEntity.setCategories(lstCategory);
        productRepository.save(productEntity);
    }

    @Override
    public void updateStatus(Long productId, ProductStatus status){
        Product product = productRepository.findById(productId).orElseThrow(()->new BussinessException(Errors.DATA_NOT_FOUND));
        product.setStatus(status);
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new BussinessException(Errors.DATA_NOT_FOUND));
        productRepository.delete(product);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new BussinessException(Errors.DATA_NOT_FOUND));
        return modelMapper.map(product, ProductDto.class);
    }

}
