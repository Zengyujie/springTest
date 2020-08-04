package com.test.spring5;

import com.alibaba.druid.pool.DruidDataSource;
import com.test.spring5.bean.Course;
import com.test.spring5.bean.Emp;
import com.test.spring5.bean.Stu;
import com.test.spring5.beanLife.MyBeanPost;
import com.test.spring5.beanLife.Orders;
import com.test.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TestDemo {


    @Test
    public void testAdd(){
        //1，加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        //2，获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
        System.out.println("-------------");
        User user2 = context.getBean("user1", User.class);
        System.out.println(user2);
        System.out.println("-------------");
        Order order1 = context.getBean("order1",Order.class);
        System.out.println(order1);

    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Emp emp = context.getBean("emp1",Emp.class);
        System.out.println(emp);
    }

    @Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
        Stu std = context.getBean("stu1", Stu.class);
        System.out.println(std);
    }

    @Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("factoryBean1.xml");
        Course course = context.getBean("myBean1",Course.class);
        System.out.println(course);
    }


    @Test
    public void test6(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beanLife1.xml");
        Orders order = context.getBean("orders1",Orders.class);
        System.out.println(order);
        //手动销毁bean实例
        ((ClassPathXmlApplicationContext)context).close();
    }

    @Test
    public void test7(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beanLife1.xml");
        MyBeanPost order = context.getBean("mybeanpost1", MyBeanPost.class);
        System.out.println(order);
        //手动销毁bean实例
        ((ClassPathXmlApplicationContext)context).close();
    }


    @Test
    public void test8(){
        ApplicationContext context = new ClassPathXmlApplicationContext("autowarp.xml");
        Emp order = context.getBean("autoemp1", Emp.class);
        System.out.println(order);
    }

    @Test
    public void test9(){
        ApplicationContext context = new ClassPathXmlApplicationContext("DruidByHand.xml");
        DataSource order = context.getBean("dataSource", DruidDataSource.class);
        try {
            System.out.println(order.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
