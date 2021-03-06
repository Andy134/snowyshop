package com.snowy.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private RoleDto role;
}
