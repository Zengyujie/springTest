package com.test1.spring5.annotation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.test1.spring5.annotation"})
@EnableAspectJAutoProxy(proxyTargetClass = true)//开启代理,等价于aop:aspectj-autoproxy
public class Config1 {

}
