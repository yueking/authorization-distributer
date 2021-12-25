package com.yueking.core.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/oauth/test")
    public Object test(){
        return "Authorization Server is ready";
    }
}
