package com.test1.spring5.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component(value = "userProxy1")
@Aspect
@Order(1)
public class UserProxy1 {

    public static final String pcut1 = "execution(public void com.test1.spring5.annotation.User.add(..))";

    //切入点抽取
    @Pointcut(value="execution(public void com.test1.spring5.annotation.User.add(..))")
    public void pointCut(){

    }



    //before注解表示前置通知
    @Before(value="pointCut()")
    public void before(){
        System.out.println("before1");
    }


    //最终通知，一定会执行
    @After(value=pcut1)
    public void after(){
        System.out.println("after1");
    }


    //返回值之后执行
    //也叫返回通知，如果遇到异常就不执行
    @AfterReturning(value="execution(* com.test1.spring5.annotation.User.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning1");
    }


    //异常通知
    @AfterThrowing(value="execution(* com.test1.spring5.annotation.User.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing1");
    }


    @Around(value="execution(* com.test1.spring5.annotation.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("around  之前1");
        proceedingJoinPoint.proceed();//切入点
        System.out.println("around  之后1");//如果遇到异常就不执行
    }


}
