package com.example.interview.controller;

import com.example.interview.model.LocalUser;
import com.example.interview.model.User;
import com.example.interview.service.UserService;
import com.example.interview.threadlocal.LocalUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        return userService.login(user);
    }

    //注册
    @PostMapping("/register")
    public Integer register(@RequestBody User user) {
        Integer register = userService.register(user);
        return register;
    }

    //获取当前用户信息
    @GetMapping("/me")
    public User me() {
        LocalUser localUser = LocalUserUtil.getLocalUser();
        String  id = localUser.getId();
        User user = userService.me(id);
        return user;
    }
}


