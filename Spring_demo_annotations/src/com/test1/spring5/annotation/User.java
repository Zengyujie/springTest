package com.test1.spring5.annotation;

import org.springframework.stereotype.Component;

@Component(value="user")
public class User {

    public void add(){
        System.out.println("add");
    }

}
