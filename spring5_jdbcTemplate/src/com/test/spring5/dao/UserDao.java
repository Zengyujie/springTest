package com.test.spring5.dao;

import com.test.spring5.pojo.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void deleteUserById(int id);

    void updateUserById(int id, User user);

    int findCount();

    User findUserById(int id);

    List<User> findAll();

    void batchAdd(List<Object[]> batchArgs);

    void batchUpdate(List<Object[]> batchArgs);

    void batchDelete(List<Object[]> batchArgs);


}
