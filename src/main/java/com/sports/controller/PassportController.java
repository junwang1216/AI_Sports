package com.sports.controller;

import com.sports.common.annotation.Desc;
import com.sports.common.annotation.NotProtected;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangjun on 2017/4/25.
 */
@Controller
@RequestMapping("/passport")
public class PassportController {

    @Desc("用户登录")
    @NotProtected
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderPassportLogin() {
        return "Passport/Login";
    }

    @Desc("用户注册")
    @NotProtected
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String renderPassportRegister() {
        return "Passport/Register";
    }

}
