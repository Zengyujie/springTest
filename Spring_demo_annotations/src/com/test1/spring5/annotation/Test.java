package com.test1.spring5.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @org.junit.Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        User user = context.getBean("user",User.class);
        user.add();
    }

    @org.junit.Test
    public void test2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Config1.class);
        User user = context.getBean("user",User.class);
        user.add();
    }

}
