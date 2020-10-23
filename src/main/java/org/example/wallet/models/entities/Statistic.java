package org.example.wallet.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "statistics")
public class Statistic extends BaseEntity {

    private BigDecimal currentAmount = BigDecimal.ZERO;
    private BigDecimal totalIncomes = BigDecimal.ZERO;
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    private Wallet wallet;

    public Statistic() {
    }

    @Column(name = "current_amount")
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Column(name = "total_incomes")
    public BigDecimal getTotalIncomes() {
        return totalIncomes;
    }

    public void setTotalIncomes(BigDecimal totalIncomes) {
        this.totalIncomes = totalIncomes;
    }

    @Column(name = "total_expenses")
    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    @OneToOne
    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
