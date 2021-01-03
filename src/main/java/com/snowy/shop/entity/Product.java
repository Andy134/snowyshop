package com.snowy.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.snowy.shop.constants.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name="product_seq", initialValue=1000, allocationSize=100)
    private long id;
    @Column(nullable = false, unique = true, length = 200)
    private String name;
    @Column(nullable = false, length = 2000)
    private String description;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private long price;
    @Column(nullable = false)
    private long commentPrice;
    @Column(nullable = false)
    private ProductStatus status;

    @ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonBackReference
    Set<Order> orders;
}
