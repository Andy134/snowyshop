package com.snowy.shop.model;

import com.snowy.shop.constants.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInsertDto {
    private long totalAmount;
    private long discountAmount;
    private OrderStatus orderStatus;
    private long [] productsIds;
    private long userId;
}
