package com.example.demospringsecurity.account;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountSupport implements ApplicationRunner {
    private final AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account user = Account.builder()
                .username("jkj")
                .password("1234")
                .role("USER")
                .build();

        Account admin = Account.builder()
                .username("admin")
                .password("1234")
                .role("ADMIN")
                .build();

        accountService.createNew(user);
        accountService.createNew(admin);
    }
}
