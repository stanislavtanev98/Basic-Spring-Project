package org.example.wallet.services;

import org.example.wallet.models.entities.User;

public interface UserService {

    User registerUser(User userServiceModel);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
