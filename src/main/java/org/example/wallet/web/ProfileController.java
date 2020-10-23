package org.example.wallet.web;

import org.example.wallet.models.entities.User;
import org.example.wallet.services.ExpenseService;
import org.example.wallet.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wallet")
public class ProfileController {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    @Autowired
    public ProfileController(IncomeService incomeService, ExpenseService expenseService) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    //    @GetMapping("/details")
//    public ModelAndView details(@RequestParam("id")String id,
//                                ModelAndView modelAndView){
//
//        modelAndView.addObject("item", this.itemService.findById(id));
//        modelAndView.setViewName("details-item");
//        return modelAndView;
//    }
    @GetMapping("/profile")
    public ModelAndView profile(ModelAndView modelAndView, HttpSession httpSession){

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.setViewName("profile");
        }

        modelAndView.addObject("expenses", this.expenseService.getExpenses((User) httpSession.getAttribute("user")));
        modelAndView.addObject("incomes", this.incomeService.getIncomes());
        modelAndView.setViewName("profile");
        return modelAndView;
    }
}
