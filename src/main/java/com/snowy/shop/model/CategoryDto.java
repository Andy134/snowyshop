package com.snowy.shop.model;

import com.snowy.shop.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CategoryDto extends BaseDto{
    private long id;
    private String name;
    private String description;
//    Set<Product> products;
}
