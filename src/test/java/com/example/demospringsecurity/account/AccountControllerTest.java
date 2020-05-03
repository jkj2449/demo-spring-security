package com.example.demospringsecurity.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Test
    @WithAnonymousUser
    public void index_anonymous() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "jkj", roles = "USER")
    public void index_user() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void admin_admin() throws Exception {
        mockMvc.perform(get("/admin").with(user("jkj").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void admin_user() throws Exception {
        mockMvc.perform(get("/admin").with(user("jkj").roles("USER")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Transactional
    @Test
    public void login() throws Exception {
        String username = "test";
        String password = "1234";
        getUser(username, password);

        mockMvc.perform(formLogin().user(username).password(password))
                .andExpect(authenticated());
    }

    @Transactional
    @Test
    public void login_fail() throws Exception {
        String username = "test1";
        String password = "12345";
        getUser(username, password);

        mockMvc.perform(formLogin().user(username).password("123"))
                .andExpect(unauthenticated());
    }

    private Account getUser(String username, String password) {
       return accountService.createNew(Account.builder()
                .username(username)
                .password(password)
                .role("USER")
                .build());
    }
}
