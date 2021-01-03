package com.snowy.shop.model;

import com.snowy.shop.constants.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class ProductInsertDto {
    private String name;
    private String description;
    private int quantity;
    private long price;
    private long commentPrice;
    private ProductStatus status;
    private long [] categoryIds;
}
