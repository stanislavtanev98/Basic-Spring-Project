package org.example.wallet.services.impl;

import org.example.wallet.models.entities.Role;
import org.example.wallet.models.entities.User;
import org.example.wallet.services.AuthService;
import org.example.wallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User register(User user) {
        user.setRole(Role.VISITOR);
        return userService.registerUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = this.userService.getUserByUsername(username);
        if(user.getPassword().equals(password)){
            return user;
        }

        return null;
    }
}
