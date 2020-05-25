package com.cc.service.impl;

import com.cc.mapper.UseMapper;
import com.cc.pojo.User;
import com.cc.service.UserService;
import com.cc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UseMapper useMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = useMapper.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
