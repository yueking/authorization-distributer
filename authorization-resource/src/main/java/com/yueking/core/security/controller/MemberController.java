package com.yueking.core.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @RequestMapping("/addMember")
    public String addMember() {
        return "addMember";
    }

    @RequestMapping("/delMember")
    public String delMember() {
        return "delMember";
    }

    @RequestMapping("/updateMember")
    public String updateMember() {
        return "updateMember";
    }

    @RequestMapping("/showMember")
    public String showMember() {
        return "showMember";
    }

}
