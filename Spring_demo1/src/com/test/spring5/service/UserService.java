package com.test.spring5.service;

import com.test.spring5.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){

        System.out.println("service add");
        userDao.update();
    }

}
