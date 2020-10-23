package org.example.wallet.services;

import org.example.wallet.models.entities.User;

public interface AuthService {
    User register(User user);
    User login(String username, String password);
}
