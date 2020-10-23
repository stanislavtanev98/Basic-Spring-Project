package org.example.wallet.services.impl;

import org.example.wallet.models.entities.Role;
import org.example.wallet.models.entities.User;
import org.example.wallet.models.entities.Wallet;
import org.example.wallet.repositories.WalletRepository;
import org.example.wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet createWallet(){
        Wallet wallet = new Wallet();
        return this.walletRepository.save(wallet);
    }
}
