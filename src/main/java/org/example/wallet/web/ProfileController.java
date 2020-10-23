package org.example.wallet.web;

import org.example.wallet.models.entities.Expense;
import org.example.wallet.models.entities.Income;
import org.example.wallet.models.entities.User;
import org.example.wallet.services.ExpenseService;
import org.example.wallet.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collection;

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

    @GetMapping("/profile")
    public ModelAndView profile(ModelAndView modelAndView, HttpSession httpSession,
                                RedirectAttributes redirectAttributes){

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            Collection<Expense> expenses = this.expenseService.getExpenses((User) httpSession.getAttribute("user"));
            Collection<Income> incomes = this.incomeService.getIncomes((User) httpSession.getAttribute("user"));
            if(expenses.size() == 0){
                redirectAttributes.addFlashAttribute("emptyExpenses", true);
            }
            if(incomes.size() == 0){
                redirectAttributes.addFlashAttribute("emptyIncomes", true);
            }
            modelAndView.addObject("expenses", expenses);
            modelAndView.addObject("incomes", incomes);
            modelAndView.setViewName("profile");
        }

        return modelAndView;
    }
}
