package org.example.wallet.services.impl;

import org.example.wallet.models.entities.Expense;
import org.example.wallet.models.entities.Statistic;
import org.example.wallet.models.entities.User;
import org.example.wallet.models.entities.Wallet;
import org.example.wallet.repositories.ExpenseRepository;
import org.example.wallet.services.ExpenseService;
import org.example.wallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void expenseMoney(User user, Expense expense) {
        if(expense.getDate() == null){
            expense.setDate(new Date());
        }
        Wallet wallet = user.getWallet();
        expense.setWallet(wallet);
        Statistic statistic = wallet.getStatistic();
        statistic.setTotalExpenses(statistic.getTotalExpenses().add(expense.getAmount()));
        statistic.setCurrentAmount(statistic.getCurrentAmount().subtract(expense.getAmount()));
        this.expenseRepository.saveAndFlush(expense);
    }

    @Override
    public Expense findById(Long id) {
        return this.expenseRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Expense> getExpenses(User user) {
        Wallet wallet = user.getWallet();
        Collection<Expense> collection = this.expenseRepository.findByWallet(wallet);
        return collection;
    }
}
