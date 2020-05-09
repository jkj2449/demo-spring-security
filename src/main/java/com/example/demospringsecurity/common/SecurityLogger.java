package com.example.demospringsecurity.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityLogger {
    public static void log(String message) {
        log.info(message);
        Thread thread = Thread.currentThread();
        log.info("Thread : {}", thread.getName());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Principal : {}", principal.toString());
    }
}
