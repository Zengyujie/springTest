package com.test1.spring5.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component(value = "userProxy")
@Aspect//生成代理对象，配置aop之后会被扫描到
public class UserProxy {

    //before注解表示前置通知
    @Before(value="execution(public void com.test1.spring5.annotation.User.add(..))")
    public void before(){
        System.out.println("before");
    }


    //最终通知，一定会执行
    @After(value="execution(* com.test1.spring5.annotation.User.add(..))")
    public void after(){
        System.out.println("after");
    }


    //返回值之后执行
    //也叫返回通知，如果遇到异常就不执行
    @AfterReturning(value="execution(* com.test1.spring5.annotation.User.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning");
    }


    //异常通知
    @AfterThrowing(value="execution(* com.test1.spring5.annotation.User.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }


    @Around(value="execution(* com.test1.spring5.annotation.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("around  之前");
        proceedingJoinPoint.proceed();//切入点
        System.out.println("around  之后");//如果遇到异常就不执行
    }

}
