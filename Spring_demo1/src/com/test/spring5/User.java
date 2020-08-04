package com.test.spring5;

public class User {

/*
一，Spring简介
1，Spring是一个轻量级的开源javaEE框架
	轻量级：引入jar包体积小，比较少，可以独立使用
2，Spring可以解决企业开发的复杂性
3，Spring有两大核心部分：IOC，AOP
	1，IOC：控制反转，把创建对象的过程交给Spring进行管理，不再用new的形式
	2，AOP：面向切面，不修改源代码的情况下进行功能的增加和增强
4，Spring框架的特点：
	1，方便解耦，简化开发
	2，AOP编程的支持
	3，方便程序的测试，整合unit
	4，方便整合各种优秀框架
	5，降低javaEE API的使用难度
	6，方便进行事务的操作

4，Spring核心容器：
	Beans，Core（前两个对应IOC），Context，Expression

5，helloworld：
    1，创建工程，2，编写要调用的类，3，编写xml配置文件，4，加载配置文件，获取配置创建对象

二，IOC：
    1，IOC概念和原理：对象的被创建的时候，由一个调控系统内所有对象的外界实体将其所依赖的对象
        引用传递给它，也可以说把对象的创建和对象之间的调用过程交给Spring进行管理
        最常见的方式有DI，依赖注入和DL，依赖查找
    2，使用IOC目的：降低耦合度
1，IOC底层原理
    1，XML解析，工厂模式，反射
        ->一个例子：UserService中有一个UserDao对象，当userdao变化时(路径，方法名等)，userservice也会变
             这样耦合度很高
        ->解决方案一：使用工厂
    2，IOC过程：（进一步降低耦合度）xml+反射+工厂
        1，xml配置文件，配置创建对象
        2，创建工厂类，工厂方法中解析xml获取class属性，然后通过反射创建对象

    3，IOC思想基于IOC容器完成，IOC容器的底层就是对象工厂
    4，Spring提供IOC容器的两种实现方式：两个接口
        1，BeanFactory：IOC容器的基本实现，是Spring内部的使用接口，不提供开发人员使用
        2，ApplicationContext：BeanFactory接口的子接口，提供了更多功能，一般面向开发人员
        区别：
        1，BeanFactory：加载配置文件时，不会创建对象，在获取时才创建对象
        2，ApplicationContext：加载配置文件时，就会创建，一般在服务器加载时将这些耗时耗资源的过程加载完成
    5，ApplicationContext的主要实现类：
        1，FileSystemXmlApplicationContext：xml就需要写上带盘符的路径
        2，ClassPathXmlApplicationContext：src下的路径

2，IOC操作 Bean管理：
    1，什么是Bean管理 ：1，spring创建对象，2，spring属性注入
    2，Bean管理操作的实现模式：1，基于xml，2，基于注解

3，基于xml创建对象：
    1，bean标签的常用属性：
        id属性:待对象类的唯一表示
        class属性:类全路径
        name属性(早期属性，不常用了)：与id类似，区别是id内不能加特殊符号，name可以
    2，创建对象时默认执行无参构造方法

3，基于xml注入属性：
    1,DI:依赖注入(注入属性)
        方式一：set方法注入(先创建对象再注入属性)
            1，创建类，定义set方法
            2，在Spring的配置文件中配置对象的创建，配置属性注入
        方式二：有参构造注入
            在Spring配置文件中的<bean>中添加属性<constructor-arg>标签
    2,xml注入其他类型的值
        1，字面量：
            1，null值

            2，属性值包含特殊符号

        2，属性：
            1，注入外部bean
                1，闯将两个类
                2，类1调用类2，(类1持有类2的引用)
                3，在类1声明中不用value而使用ref

            2，注入内部bean和级联复制
                内部注入就是把对象声明在属性内嵌套<bean>，不写value
                级联赋值，就是对ref引用或者内部的对象都赋值

            3，注入数组，list，map

            4，注入抽取结构

4，FactoryBean
    1，Spring有两种类型的bean，普通bean(配置文件中定义的类型就是返回类型)和工厂bean(配置文件中定义的可以和返回的不一样)
    使用方法：
        1，创建类，让这个类作为工厂bean，实现接口FactoryBean
        2，实现接口中的方法，在实现的方法中的getObject方法中定义返回的bean类型


5，bean的作用域
    1，在Spring里，设置创建bean实例是单实例还是多实例
    2，Spring在默认情况下，创建的bean是单实例对象
    3，设置bean是单实例还是多实例：<bean scope="singleton">
        1,singleton:单实例
            加载spring配置文件的时候就会创建单实例对象
        2,prototype：多实例
            不是在加载配置文件时创建，在getBean方法时创建对象，每次建的都是一个新的对象
        3,request:创建时放入request中
        4,session:创建时放入session中
        5,


6，bean的生命周期
    生命周期：从对象的创建到销毁的过程
    1，通过构造器创建bean实例(无参构造)
    2，为bean的属性设置值 和对其他bean的引用(调用set方法)
    3，调用bean的初始化方法(需要进行配置)
    4，bean可以使用了(获取到了)
    5，当容器关闭时，调用bean的销毁方法(需要进行配置)

    加上bean的后置处理器之后生命周期变为七步：
        1，通过构造器创建bean实例(无参构造)
        2，为bean的属性设置值 和对其他bean的引用(调用set方法)
        3，把bean实例传递给bean后置处理器的方法
        4，调用bean的初始化方法(需要进行配置)
        5，把bean实例传递给后置处理器的方法
        6，bean可以使用了(获取到了)
        7，当容器关闭时，调用bean的销毁方法(需要进行配置)

   添加后置处理器
   1，让类实现BeanPostProcessor接口
   2，配置文件中配置的实现后置处理器的类会在配置文件中每一个类的初始化时调用方法

IOC自动装配
    手动装配：手动在配置文件中设置<property>属性
    自动装配免去了手动装配的步骤
    1，根据指定的装配规则(属性名称或者属性类型)，Spring自动将匹配的属性值进行注入
    一般在实际中，用xml很少，一般都使用注解

IOC引入外部的属性文件
    1，直接配置数据库信息
    2，引入外部文件配置
        1，引入名称空间context
        2, 配置location的值，值为配置文件的路径
        3，使用EL表达式填入value中，表达式的值为配置文件的key条目

===========================

基于注解方式管理Bean
1，Spring针对Bean管理中创建对象提供的注解：
    1，@Component：普通的注解，用来来标注一般对象
    2，@Service：一般用在业务逻辑层
    3，@Controller：一般用在Web层
    4，@Repository：一般用在Dao层或持久层上
    以上四个注解功能是一样的，都可以用来创建bean实例，只是习惯把不同注解用于不同的情况

2，基于注解方式实现对象创建


 */

    private String name;
    private Integer id;

    public void add(){
        System.out.println("uesr add");
    }

    public User() {

    }

    //有参构造注入


    public User(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    //通过get方法注入属性
    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
