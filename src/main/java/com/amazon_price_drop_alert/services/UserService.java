package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.User;
import com.amazon_price_drop_alert.dtos.UserDto;
import com.amazon_price_drop_alert.exceptions.UserAlreadyBlockedException;
import com.amazon_price_drop_alert.exceptions.UserAlreadyExists;
import com.amazon_price_drop_alert.exceptions.UserAlreadyUnblockedException;
import com.amazon_price_drop_alert.exceptions.UserNotFoundException;

import java.util.List;


public interface UserService {

    User getUserById(final Long id) throws UserNotFoundException;

    User createUser(final User user) throws UserAlreadyExists;

    void blockUser(final Long id) throws UserAlreadyBlockedException, UserNotFoundException;

    void unblockUser(final Long id) throws UserAlreadyUnblockedException, UserNotFoundException;

    void deleteUser(final Long id);

    List<User> getUsers();


}
