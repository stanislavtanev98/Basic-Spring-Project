package org.example.wallet.web;

import lombok.extern.slf4j.Slf4j;
import org.example.wallet.models.entities.User;
import org.example.wallet.services.AuthService;
import org.example.wallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult) {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView createNewUser(@Valid @ModelAttribute("user")
                                             User user,
                                      BindingResult result, ModelAndView modelAndView,
                                      RedirectAttributes redirectAttributes) {

        User existingUsername = this.userService.getUserByUsername(user.getUsername());
        User existingEmail = this.userService.getUserByEmail(user.getEmail());

        if(result.hasErrors() ||
                !user.getPassword().equals(user.getConfirmPassword()) ||
                existingUsername != null ||
                existingEmail != null){
            if(existingUsername != null) {
                redirectAttributes.addFlashAttribute("existingUsername", true);
            }
            if(existingEmail != null){
                redirectAttributes.addFlashAttribute("existingEmail", true);
            }
            if(!user.getPassword().equals(user.getConfirmPassword())){
                redirectAttributes.addFlashAttribute("notFound", true);
            }
            redirectAttributes.addFlashAttribute("user", user);
            modelAndView.setViewName("redirect:/users/register");
        } else {
            this.authService.register(user);
            modelAndView.setViewName("redirect:/users/login");
        }

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("user")User user,
                              BindingResult bindingResult,
                              ModelAndView modelAndView) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute("user")User user,
                        BindingResult bindingResult, ModelAndView modelAndView,
                                     HttpSession httpSession, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            modelAndView.setViewName("redirect:/users/login");
        } else {
            User confirmUser = this.userService.getUserByUsername(user.getUsername());

            if(confirmUser == null || !user.getPassword().equals(confirmUser.getPassword())){
                redirectAttributes.addFlashAttribute("notFound", true);
                redirectAttributes.addFlashAttribute("user", user);
                modelAndView.setViewName("redirect:/users/login");
            } else {
                httpSession.setAttribute("user", confirmUser);
                httpSession.setAttribute("id", confirmUser.getId());
                httpSession.setAttribute("role", confirmUser.getRole());
                modelAndView.setViewName("redirect:/");
            }
        }

        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
}
