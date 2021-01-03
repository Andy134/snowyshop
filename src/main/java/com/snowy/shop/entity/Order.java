package com.snowy.shop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.snowy.shop.constants.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SaleOrder")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name="order_seq", initialValue=10000, allocationSize=100)
    private long id;

    @OneToOne
    private User user;

    @ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Product> products;

    @Column
    private long totalAmount;

    @Column
    private long discountAmount;

    @Column
    private OrderStatus orderStatus;
}
