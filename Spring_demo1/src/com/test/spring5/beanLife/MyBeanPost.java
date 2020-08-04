package com.test.spring5.beanLife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPost implements BeanPostProcessor {

    private String oname;

    public MyBeanPost() {
        System.out.println("post的无参构造");
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        System.out.println("post调用set方法设置oname");
        this.oname = oname;
    }

    //创建一个初始化方法
    public void initMethod(){
        System.out.println("post执行初始化方法");
    }

    @Override
    public String toString() {
        return "MyBeanPost{" +
                "oname='" + oname + '\'' +
                '}';
    }


    //创建销毁方法
    public void destoryMethod(){
        System.out.println("post销毁order对象");
    }

    //后置处理接口方法实现：


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("post初始化之前");
        return bean;//传入的bean就是创建bean对象
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("post初始化之后");
        return bean;
    }
}
