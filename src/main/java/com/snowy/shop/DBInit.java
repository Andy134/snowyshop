package com.snowy.shop;

import com.snowy.shop.constants.OrderStatus;
import com.snowy.shop.constants.ProductStatus;
import com.snowy.shop.entity.*;
import com.snowy.shop.repository.CategoryRepository;
import com.snowy.shop.repository.OrderRepository;
import com.snowy.shop.repository.ProductRepository;
import com.snowy.shop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
class DBInit implements CommandLineRunner {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    DBInit(ProductRepository productRepo, CategoryRepository categoryRepo, OrderRepository orderRepository, UserRepository userRepository) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Random random = new Random();
        int productNo = 5;
        Set<Category> categories = new HashSet<>();
        int categoryNo = 3;
        for (int i = 0; i < categoryNo; i++) {
            categories.add(Category.builder().name("Danh muc " + i).description("DM" + i)
                    .build());
        }
        Set<Product> products = new HashSet<>();
        for (int i = 0; i < productNo; i++) {
            products.add(Product.builder().name("San pham " + i).description("SP" + i)
                    .price(1000000l)
                    .quantity(100)
                    .status(ProductStatus.ACTIVE)
                    .categories(categories)
                    .build());
        }
        productRepo.saveAll(products);

        Role roleAdmin = Role.builder().name("ADMIN").build();
        Role roleUser = Role.builder().name("USER").build();

        Set<User> setOfUser = new HashSet<>();
        User admin = User.builder().name("admin admin").role(roleAdmin).build();
        User user = User.builder().name("user user").role(roleUser).build();
        setOfUser.add(admin);
        setOfUser.add(user);

        userRepository.saveAll(setOfUser);

        // Order
        int orderNo = 5;
        Set<Order> orders = new HashSet<>();
        for (int i = 0; i < orderNo; i++) {
            orders.add(
                Order.builder()
                    .products(products)
                    .orderStatus(OrderStatus.CHECKOUT)
                    .totalAmount(100000)
                    .discountAmount(90000)
                    .user(user)
                    .build()
            );
        }
        orderRepository.saveAll(orders);
    }
}
