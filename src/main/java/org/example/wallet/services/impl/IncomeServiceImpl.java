package org.example.wallet.services.impl;

import org.example.wallet.models.entities.Income;
import org.example.wallet.models.entities.User;
import org.example.wallet.models.entities.Wallet;
import org.example.wallet.repositories.IncomeRepository;
import org.example.wallet.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public void addMoney(User user, Income income) {
        if(income.getDate() == null){
            income.setDate(new Date());
        }
        Wallet wallet = user.getWallet();
        income.setWallet(wallet);
        this.incomeRepository.saveAndFlush(income);
    }

    @Override
    public Collection<Income> getIncomes() {
        return this.incomeRepository.findAll();
    }
}
