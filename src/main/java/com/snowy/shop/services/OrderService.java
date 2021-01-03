package com.snowy.shop.services;

import com.snowy.shop.constants.OrderStatus;
import com.snowy.shop.model.OrderDto;
import com.snowy.shop.model.OrderInsertDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDto> findAllByStatus(OrderStatus status, Pageable pageable);

    void save(OrderInsertDto productDto);

    void updateStatus(Long id, OrderStatus status);

    void delete(Long id);

    OrderDto findById(Long id);
}
