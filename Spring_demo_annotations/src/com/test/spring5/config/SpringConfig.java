package com.test.spring5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //增加该注解表示使其作为配置类代替xml
@ComponentScan(basePackages = {"com.test.spring5.test","com.test.spring5.service","com.test.spring5.dao"})//指定要扫描的包
public class SpringConfig {

}

