package org.example.wallet.web;

import org.example.wallet.models.entities.User;
import org.example.wallet.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final StatisticService statisticService;

    @Autowired
    public HomeController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
//            User user = (User) httpSession.getAttribute("user");
//            modelAndView.addObject("statistic", this.statisticService.getStatistic(user));
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }

}
