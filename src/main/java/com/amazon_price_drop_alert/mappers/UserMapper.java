package com.amazon_price_drop_alert.mappers;

import com.amazon_price_drop_alert.domains.User;
import com.amazon_price_drop_alert.dtos.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getMail(),
                userDto.isActive(),
                userDto.getSearchedProduct());
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getMail(),
                user.isActive(),
                user.getSearchedProducts());
    }
    public List<UserDto> mapToUserDtoList(List<User> users){
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users
             ) {
            userDtoList.add(mapToUserDto(user));
        }
        return userDtoList;
    }

}
