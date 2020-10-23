package org.example.wallet.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "wallets")
public class Wallet extends BaseEntity {

    private User user;
    private Set<Expense> expenses;
    private Set<Income> incomes;

    public Wallet() {
    }

    @OneToOne(mappedBy = "wallet")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }
}
