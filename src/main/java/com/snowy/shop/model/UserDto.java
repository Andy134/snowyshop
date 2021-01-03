package com.snowy.shop.model;

import com.snowy.shop.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private Role role;
}
