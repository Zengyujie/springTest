package com.test.spring5.factoryBean;

import com.test.spring5.bean.Course;
import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Course> {

    //定义返回的bean
    @Override
    public Course getObject() throws Exception {
        Course c = new Course();
        c.setCname("english");
        return  c;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }


}
