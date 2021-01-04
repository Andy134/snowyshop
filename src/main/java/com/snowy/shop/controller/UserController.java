package com.snowy.shop.controller;

import com.snowy.shop.entity.Role;
import com.snowy.shop.entity.User;
import com.snowy.shop.error.BusinessException;
import com.snowy.shop.error.Errors;
import com.snowy.shop.model.UserCreationDto;
import com.snowy.shop.model.UserDto;
import com.snowy.shop.repository.RoleRepository;
import com.snowy.shop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new BusinessException(Errors.DATA_NOT_FOUND));
        return modelMapper.map(entity, UserDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newUser(@RequestBody UserCreationDto dto) {
        Role role = roleRepository.findById(dto.getRoleId()).orElseThrow(() -> new BusinessException(Errors.DATA_NOT_FOUND));
        User entity = modelMapper.map(dto, User.class);
        entity.setRole(role);
        userRepository.save(entity);
    }

}
