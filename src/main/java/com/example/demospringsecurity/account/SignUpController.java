package com.example.demospringsecurity.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SignUpController {
    private final AccountService accountService;

    @GetMapping
    public String signUpForm(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    @PostMapping
    public String signUp(@ModelAttribute Account account) {
        account.setRole("USER");
        accountService.createNew(account);
        return "redirect:/";
    }
}
