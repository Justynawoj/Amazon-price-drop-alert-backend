package com.amazon_price_drop_alert.services;

import com.amazon_price_drop_alert.domains.User;
import com.amazon_price_drop_alert.exceptions.UserAlreadyBlockedException;
import com.amazon_price_drop_alert.exceptions.UserAlreadyExists;
import com.amazon_price_drop_alert.exceptions.UserAlreadyUnblockedException;
import com.amazon_price_drop_alert.exceptions.UserNotFoundException;
import com.amazon_price_drop_alert.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(final Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + "is not present in DB"));

    }

    @Override
    public User createUser(User user) throws UserAlreadyExists {
          if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
        return userRepository.save(user);
        } else {
            throw new UserAlreadyExists("User with that email already exists");
        }
    }

    @Override
    public void blockUser(final Long id) throws UserAlreadyBlockedException, UserNotFoundException {
        User user = getUserById(id);
        if (!user.isActive()) {
            throw new UserAlreadyBlockedException("User already blocked");
        } else {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public void unblockUser(final Long id) throws UserAlreadyUnblockedException, UserNotFoundException {
        User user = getUserById(id);
        if (user.isActive()) {
            throw new UserAlreadyUnblockedException("User is not blocked");
        } else {
            user.setActive(true);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }


}
