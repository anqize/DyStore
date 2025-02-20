package com.store.service;

import com.store.beans.LoginInfo;
import com.store.beans.Users;

public interface UserService {

    LoginInfo login(Users users);

    LoginInfo signup(Users user);
}
