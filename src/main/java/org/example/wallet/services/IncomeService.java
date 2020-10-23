package org.example.wallet.services;

import org.example.wallet.models.entities.Expense;
import org.example.wallet.models.entities.Income;
import org.example.wallet.models.entities.User;

import java.util.Collection;

public interface IncomeService {

    void addMoney(User user, Income income);

    Collection<Income> getIncomes(User user);
}
