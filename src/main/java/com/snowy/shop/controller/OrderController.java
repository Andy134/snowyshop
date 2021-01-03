package com.snowy.shop.controller;

import com.snowy.shop.constants.OrderStatus;
import com.snowy.shop.model.OrderDto;
import com.snowy.shop.model.OrderInsertDto;
import com.snowy.shop.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<OrderDto> getAllCheckout(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.findAllByStatus(OrderStatus.CHECKOUT, pageable);
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody OrderInsertDto dto) {
        orderService.save(dto);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateStatus(@PathVariable Long id, @RequestBody OrderStatus status) {
        orderService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
