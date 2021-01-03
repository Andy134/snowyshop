package com.snowy.shop.model;

import com.snowy.shop.constants.ProductStatus;
import com.snowy.shop.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CategoryInsertDto {
    private long id;
    private String name;
    private String description;
}
