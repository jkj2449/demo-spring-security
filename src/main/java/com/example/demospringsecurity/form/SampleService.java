package com.example.demospringsecurity.form;

import com.example.demospringsecurity.common.SecurityLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class SampleService {

    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Principal과 GrantAuthority 제공
        Object principal = authentication.getPrincipal(); //UserDetailService에서 return한 UserDetail
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // 권한
        Object credentials = authentication.getCredentials();
    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async service is called");
    }
}
