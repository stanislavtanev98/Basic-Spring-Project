package org.example.wallet.services.impl;

import org.example.wallet.models.entities.Statistic;
import org.example.wallet.models.entities.Wallet;
import org.example.wallet.repositories.WalletRepository;
import org.example.wallet.services.StatisticService;
import org.example.wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final StatisticService statisticService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, StatisticService statisticService) {
        this.walletRepository = walletRepository;
        this.statisticService = statisticService;
    }

    @Override
    public Wallet createWallet(){
        Wallet wallet = new Wallet();
        wallet.setStatistic(this.statisticService.createStatistic());
        Statistic statistic = wallet.getStatistic();
        statistic.setWallet(wallet);
        return this.walletRepository.saveAndFlush(wallet);
    }
}
