package org.example.wallet.repositories;

import org.example.wallet.models.entities.Income;
import org.example.wallet.models.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    Collection<Income> findByWallet(Wallet wallet);
}
