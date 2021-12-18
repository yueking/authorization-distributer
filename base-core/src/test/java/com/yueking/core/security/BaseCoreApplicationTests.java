package com.yueking.core.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@SpringBootTest
class BaseCoreApplicationTests {
    @Resource
    UserDetailsService userDetailsService;

    @Test
    void contextLoads() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        System.out.println(userDetails);
    }

}
