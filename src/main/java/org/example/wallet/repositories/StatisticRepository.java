package org.example.wallet.repositories;

import org.example.wallet.models.entities.Statistic;
import org.example.wallet.models.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    Statistic findByWallet(Wallet wallet);

//    @Query("UPDATE Statistic AS s SET s.current_amount = :current_amount WHERE s.id = :id")
//    void updateCurrentAmount(@Param("current_amount") BigDecimal amount, @Param("id") long id);
//
//    void updateExpenseAmount();
//    void updateIncomeAmount();
}
