package org.example.wallet.services;

import org.example.wallet.models.entities.Expense;
import org.example.wallet.models.entities.User;

import java.util.Collection;

public interface ExpenseService {

    void expenseMoney(User user, Expense expense);

    Expense findById(Long id);

    Collection<Expense> getExpenses(User user);
}
