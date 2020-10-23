package org.example.wallet.repositories;

import org.example.wallet.models.entities.Expense;
import org.example.wallet.models.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Collection<Expense> findByWallet(Wallet wallet);

}
