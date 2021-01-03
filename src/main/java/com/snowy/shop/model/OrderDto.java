package com.snowy.shop.model;

import com.snowy.shop.constants.OrderStatus;
import com.snowy.shop.entity.Product;
import com.snowy.shop.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderDto extends BaseDto{
    private long id;
    private User user;
    Set<Product> products;
    private long totalAmount;
    private long discountAmount;
    private OrderStatus orderStatus;
}
