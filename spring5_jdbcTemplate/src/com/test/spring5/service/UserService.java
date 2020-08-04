package com.test.spring5.service;


import com.test.spring5.dao.UserDao;
import com.test.spring5.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value="userService")
public class UserService {

    @Autowired
    private UserDao userDao;


    public void addUser(User user){
        userDao.addUser(user);
    }

    public void updateUser(User user){
        userDao.updateUserById(user.getId(), user);
    }

    public void deleteUser(int id){
        userDao.deleteUserById(id);
    }

    public void findCount(){
        userDao.findCount();
    }

    public User findUserInfo(int id){
        return userDao.findUserById(id);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public void batchAdd(List<Object[]> batchArgs){
        userDao.batchAdd(batchArgs);
    }

    public void batchUpdate(List<Object[]> batchArgs){
        userDao.batchUpdate(batchArgs);
    }

    public void batchDelete(List<Object[]> batchArgs){
        userDao.batchDelete(batchArgs);
    }
}
