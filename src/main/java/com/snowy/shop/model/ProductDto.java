package com.snowy.shop.model;

import com.snowy.shop.constants.ProductStatus;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = false, length = 200)
    private String name;
    @Column(nullable = false, unique = false, length = 2000)
    private String description;
    @Column
    private int quantity;
    @Column
    private long price;
    @Column
    private long commentPrice;
    @Column
    private ProductStatus status;
}
