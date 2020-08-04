package com.test.spring5.service;


import com.test.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//注解里面的value属性值可以省略不写，默认值就是类的名称，首字母小写
@Component(value="userService")//等价于<bean id="userService" class="...">
public class UserService {

//    @Autowired
//    @Qualifier(value = "userDaoImpl")
//    private UserDao userDao;

    //@Resource类型注入
    @Resource(name="userDaoImpl")//根据名称注入
    private UserDao userDao;

    @Value(value="abc")
    private String name;

    @Value(value="1")
    private int id;

    public void add(){
        System.out.println("user add with annotations");
        System.out.println(name);
        System.out.println(id);
        userDao.add();
    }


}
