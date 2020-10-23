package org.example.wallet.services.impl;

import org.example.wallet.models.entities.User;
import org.example.wallet.services.UserService;
import org.example.wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.wallet.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletService walletService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    @Override
    public User registerUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            //TODO: clear exceptions
            throw new RuntimeException(String.format("User with username '%s' already exists.", user.getUsername()));
        });

        user.setWallet(this.walletService.createWallet());

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }
}
