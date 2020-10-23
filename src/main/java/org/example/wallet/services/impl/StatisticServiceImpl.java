package org.example.wallet.services.impl;

import org.example.wallet.models.entities.Statistic;
import org.example.wallet.models.entities.User;
import org.example.wallet.models.entities.Wallet;
import org.example.wallet.repositories.StatisticRepository;
import org.example.wallet.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Statistic createStatistic() {
        Statistic statistic = new Statistic();
        return this.statisticRepository.saveAndFlush(statistic);
    }

    @Override
    public Statistic getStatistic(User user) {
        Wallet wallet = user.getWallet();
        return this.statisticRepository.findByWallet(wallet);
    }

//    @Override
//    public void updateCurrent(BigDecimal value, Statistic statistic) {
//        this.statisticRepository.updateCurrentAmount(value, statistic.getId());
//    }


}
