package com.snowy.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Role extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name="role_seq", initialValue=100, allocationSize=100)
    private long id;
    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
