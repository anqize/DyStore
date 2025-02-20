package com.store.service.impl;

import com.store.beans.LoginInfo;
import com.store.beans.Users;
import com.store.mapper.UserMapper;
import com.store.service.UserService;
import com.store.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper um;

    @Override
    public LoginInfo login(Users users) {
        Users user = um.SelectByUsernameAndPassword(users);
        if(user == null){
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("name",user.getName());
        String token = JwtUtil.GenerateJwt(map);
        return new LoginInfo(user.getId(),user.getUsername(), user.getName(),token);
    }

    @Override
    public LoginInfo signup(Users users) {
        Users user = um.SelectByUsername(users);
        if(user == null){
            users.setEntryDate(LocalDate.now());
            users.setImage("12345.jpg");
            users.setName("新用户");
            users.setGender(1);
            um.signup(users);
            Users newUser = um.SelectByUsername(users);
            return new LoginInfo(newUser.getId(),newUser.getUsername(), newUser.getName(),"");
        }
        return null;
    }
}
