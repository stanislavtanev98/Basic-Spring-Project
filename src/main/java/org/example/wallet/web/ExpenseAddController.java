package org.example.wallet.web;

import org.example.wallet.models.entities.Expense;
import org.example.wallet.models.entities.Income;
import org.example.wallet.models.entities.User;
import org.example.wallet.services.ExpenseService;
import org.example.wallet.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/wallet")
public class ExpenseAddController {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @Autowired
    public ExpenseAddController(ExpenseService expenseService, IncomeService incomeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    @GetMapping("/expense")
    public ModelAndView expense(@Valid @ModelAttribute("expense")Expense expense,
                               BindingResult bindingResult, HttpSession httpSession,
                               ModelAndView modelAndView) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.setViewName("expense");
        }

        return modelAndView;
    }

    @PostMapping("/expense")
    public ModelAndView expenseMoney(@Valid @ModelAttribute("expense") Expense expense,
                                BindingResult bindingResult, ModelAndView modelAndView,
                                RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors() ||
                expense.getCategory() == null ||
                expense.getTransaction() == null ||
                expense.getAmount().intValue() <= 0) {
            if (expense.getCategory() == null) {
                redirectAttributes.addFlashAttribute("existingCategory", true);
            }
            if (expense.getTransaction() == null) {
                redirectAttributes.addFlashAttribute("existingTransaction", true);
            }
            if(expense.getAmount().intValue() <= 0){
                redirectAttributes.addFlashAttribute("negativeAmount", true);
            }
            redirectAttributes.addFlashAttribute("expense", expense);
            modelAndView.setViewName("redirect:/wallet/expense");
        } else {
            this.expenseService.expenseMoney((User) httpSession.getAttribute("user"), expense);
            modelAndView.setViewName("redirect:/wallet/profile");
        }

        return modelAndView;
    }

    @GetMapping("/income")
    public ModelAndView income(@Valid @ModelAttribute("income")Income income,
                         BindingResult bindingResult, HttpSession httpSession,
                         ModelAndView modelAndView) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.setViewName("income");
        }

        return modelAndView;
    }

    @PostMapping("/income")
    public ModelAndView incomeMoney(@Valid @ModelAttribute("income")Income income,
                                    BindingResult bindingResult, ModelAndView modelAndView,
                                    RedirectAttributes redirectAttributes, HttpSession httpSession){

        if (bindingResult.hasErrors() ||
                income.getCategory() == null ||
                income.getTransaction() == null ||
                income.getAmount().intValue() <= 0) {
            if (income.getCategory() == null) {
                redirectAttributes.addFlashAttribute("existingCategory", true);
            }
            if (income.getTransaction() == null) {
                redirectAttributes.addFlashAttribute("existingTransaction", true);
            }
            if(income.getAmount().intValue() <= 0){
                redirectAttributes.addFlashAttribute("negativeAmount", true);
            }
            redirectAttributes.addFlashAttribute("income", income);
            modelAndView.setViewName("redirect:/wallet/income");
        } else {
            this.incomeService.addMoney((User) httpSession.getAttribute("user"), income);
            modelAndView.setViewName("redirect:/wallet/profile");
        }

        return modelAndView;
    }
}
