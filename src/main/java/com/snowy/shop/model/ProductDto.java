package com.snowy.shop.model;

import com.snowy.shop.constants.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ProductDto extends BaseDto{
    private long id;
    private String name;
    private String description;
    private int quantity;
    private long price;
    private long commentPrice;
    private ProductStatus status;
    private Set<CategoryDto> categories;
}
