package com.snowy.shop.services.impl;

import com.snowy.shop.constants.OrderStatus;
import com.snowy.shop.entity.Order;
import com.snowy.shop.entity.Product;
import com.snowy.shop.entity.User;
import com.snowy.shop.error.BusinessException;
import com.snowy.shop.error.Errors;
import com.snowy.shop.model.OrderDto;
import com.snowy.shop.model.OrderInsertDto;
import com.snowy.shop.repository.OrderRepository;
import com.snowy.shop.repository.ProductRepository;
import com.snowy.shop.repository.UserRepository;
import com.snowy.shop.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<OrderDto> findAllByStatus(OrderStatus status, Pageable pageable) {
        List<OrderDto> dtos = new ArrayList<>();
        Page<Order> entities = orderRepository.findAllByOrderStatus(status, pageable);
        entities.forEach(entity -> {
            dtos.add(modelMapper.map(entity, OrderDto.class));
        });
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    @Override
    public void save(OrderInsertDto reqDto) {
        Order entity = modelMapper.map(reqDto, Order.class);

        Set<Product> lstProduct = Arrays.stream(reqDto.getProductsIds()).mapToObj(id -> productRepository.findById(id).orElseThrow(() -> new BusinessException(Errors.DATA_NOT_FOUND))).collect(Collectors.toSet());
        entity.setProducts(lstProduct);
        // add user
        Optional<User> user = userRepository.findById(reqDto.getUserId());
        entity.setUser(user.get());
        orderRepository.save(entity);
    }

    @Override
    public void updateStatus(Long id, OrderStatus status) {
        Order entity = orderRepository.findById(id).orElseThrow(()->new BusinessException(Errors.DATA_NOT_FOUND));
        entity.setOrderStatus(status);
        orderRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Order entity = orderRepository.findById(id).orElseThrow(()->new BusinessException(Errors.DATA_NOT_FOUND));
        orderRepository.delete(entity);
    }

    @Override
    public OrderDto findById(Long id) {
        Order entity = orderRepository.findById(id).orElseThrow(()->new BusinessException(Errors.DATA_NOT_FOUND));
        return modelMapper.map(entity, OrderDto.class);
    }
}
