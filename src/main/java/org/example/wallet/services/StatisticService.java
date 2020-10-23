package org.example.wallet.services;

import org.example.wallet.models.entities.Statistic;
import org.example.wallet.models.entities.User;
import org.example.wallet.models.entities.Wallet;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface StatisticService {

    Statistic createStatistic();

    Statistic getStatistic(User user);

//    void updateCurrent(BigDecimal value, Statistic statistic);
}
