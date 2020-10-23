package org.example.wallet.services.impl;

import org.example.wallet.models.entities.Income;
import org.example.wallet.models.entities.Statistic;
import org.example.wallet.models.entities.User;
import org.example.wallet.models.entities.Wallet;
import org.example.wallet.repositories.IncomeRepository;
import org.example.wallet.services.IncomeService;
import org.example.wallet.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final StatisticService statisticService;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository, StatisticService statisticService) {
        this.incomeRepository = incomeRepository;
        this.statisticService = statisticService;
    }

    @Override
    public void addMoney(User user, Income income) {
        if(income.getDate() == null){
            income.setDate(new Date());
        }
        Wallet wallet = user.getWallet();
        income.setWallet(wallet);

//        Statistic statistic = wallet.getStatistic();
//        this.statisticService.updateCurrent(statistic.getTotalIncomes().add(income.getAmount()), statistic);
//        statistic.setCurrentAmount(statistic.getCurrentAmount().add(income.getAmount()));

        this.incomeRepository.saveAndFlush(income);
    }

    @Override
    public Collection<Income> getIncomes(User user) {
        Wallet wallet = user.getWallet();
        return this.incomeRepository.findByWallet(wallet);
    }
}
