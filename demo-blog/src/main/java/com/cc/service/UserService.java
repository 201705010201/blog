package com.cc.service;

import com.cc.pojo.User;

public interface UserService {

    User checkUser(String username, String password);
}
