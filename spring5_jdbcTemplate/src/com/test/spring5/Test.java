package com.test.spring5;

import com.test.spring5.config.TXConfig;
import com.test.spring5.pojo.User;
import com.test.spring5.service.AccountService;
import com.test.spring5.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Test {

    @org.junit.Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        User user = context.getBean("user",User.class);
        user.setUserName("郑艺琳");
        user.setPassword("yerin");
        user.setEmail("yerin@sourceMusic.com");
        userService.addUser(user);
    }

    @org.junit.Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        User user = context.getBean("user", User.class);
        user.setId(3);
        user.setUserName("丁恩妃");
        user.setPassword("eunha");
        user.setEmail("eunha@sourceMusic.com");
        userService.updateUser(user);
    }

    @org.junit.Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.deleteUser(3);
    }

    @org.junit.Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.findCount();
        User u = userService.findUserInfo(1);
        System.out.println(u);
    }


    @org.junit.Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.findCount();
        List<User> list = userService.findAll();
        System.out.println(list);
    }



    @org.junit.Test
    public void test6(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        List<Object[]> list = new ArrayList<>();
        Object[] o1 = {"郑艺琳","yerin","yerin@sourceMusic.com"};
        Object[] o2 = {"丁恩妃","eunha","eunha@sourceMusic.com"};
        Object[] o3 = {"黄恩妃","sinb","sinb@sourceMusic.com"};
        list.add(o1);
        list.add(o2);
        list.add(o3);
        userService.batchAdd(list);
    }

    @org.junit.Test
    public void test7(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        List<Object[]> list = new ArrayList<>();
        Object[] o1 = {"郑艺琳1","yerin","yerin@sourceMusic.com",4};
        Object[] o2 = {"丁恩妃1","eunha","eunha@sourceMusic.com",5};
        Object[] o3 = {"黄恩妃1","sinb","sinb@sourceMusic.com",6};
        list.add(o1);
        list.add(o2);
        list.add(o3);
        userService.batchUpdate(list);
    }

    @org.junit.Test
    public void test8(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        List<Object[]> list = new ArrayList<>();
        Object[] o1 = {4};
        Object[] o2 = {5};
        Object[] o3 = {6};
        list.add(o1);
        list.add(o2);
        list.add(o3);
        userService.batchDelete(list);
    }


    @org.junit.Test
    public void test9(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        AccountService accountService= context.getBean("accountService", AccountService.class);
        accountService.accountMoneyByTx();
    }


    @org.junit.Test
    public void test10(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TXConfig.class);
        AccountService accountService= context.getBean("accountService", AccountService.class);
        accountService.accountMoneyByTx();
    }


}
