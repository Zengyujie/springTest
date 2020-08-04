package com.test.spring5;

import com.test.spring5.config.SpringConfig;
import com.test.spring5.dao.UserDao;
import com.test.spring5.dao.UserDaoImpl;
import com.test.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class TestDemo {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }

    @Test
    public void test2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }


    @Test
    public void test3(){
        UserDaoImpl us = new UserDaoImpl();
        UserDao userDao = (UserDao) Proxy.newProxyInstance(UserDao.class.getClassLoader(), new Class[]{UserDao.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("处理前");
                Object res = method.invoke(us, args);
                System.out.println("处理后");
                return null;
            }
        });
        userDao.add();
    }

}
