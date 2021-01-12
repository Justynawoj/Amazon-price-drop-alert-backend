package com.amazon_price_drop_alert.controllers;

import com.amazon_price_drop_alert.dtos.UserDto;
import com.amazon_price_drop_alert.exceptions.UserAlreadyBlockedException;
import com.amazon_price_drop_alert.exceptions.UserAlreadyExists;
import com.amazon_price_drop_alert.exceptions.UserAlreadyUnblockedException;
import com.amazon_price_drop_alert.exceptions.UserNotFoundException;
import com.amazon_price_drop_alert.mappers.UserMapper;
import com.amazon_price_drop_alert.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private final UserServiceImpl userService;
    private final UserMapper userMapper;


    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable final Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(id));
    }
    @GetMapping
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userService.getUsers());
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) throws UserAlreadyExists {
        userService.createUser(userMapper.mapToUser(userDto));
    }

    @PutMapping("block/{id}")
    public void blockUser(@PathVariable Long id) throws UserAlreadyBlockedException, UserNotFoundException {
        userService.blockUser(id);
    }

    @PutMapping("unblock/{id}")
    public void unBlockUser(@PathVariable Long id) throws UserAlreadyUnblockedException, UserNotFoundException {
        userService.unblockUser(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }


}

