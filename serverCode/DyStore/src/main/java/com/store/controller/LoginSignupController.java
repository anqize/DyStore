package com.store.controller;

import com.store.beans.LoginInfo;
import com.store.beans.Result;
import com.store.beans.Users;
import com.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
//@RequestMapping
public class LoginSignupController {
    @Autowired
    private UserService us;
    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Users user){
        LoginInfo info = us.login(user);
        log.info("登录账号: {}",user);
        if(info == null){
            log.info("{}",Result.error("用户名或密码错误"));
            return Result.error("用户名或密码错误");
        }
        log.info("{}",Result.success(info));
        return Result.success(info);
    }

    //注册
    @PostMapping("/signup")
    public Result signup(@RequestBody Users user){
        LoginInfo info = us.signup(user);
        log.info("注册账号: {}",user);
        if(info == null){
            return Result.error("该用户名已被占用或手机号已被绑定");
        }
        return Result.success(info);
    }

    //登出
    @PostMapping("/logout")
    public Result logout(@RequestBody LoginInfo info){
        info.setToken("");
        return Result.success();
    }
}
