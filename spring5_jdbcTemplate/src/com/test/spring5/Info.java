package com.test.spring5;

public class Info {

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
    1，引入依赖，注解需要引入aop jar包的依赖
    2，开启组件扫描，指定要扫描的位置，让spring知道去何处扫描
    3，创建类，添加创建对象注解

3，基于注解方式的属性注入：
    1，@AutoWired：根据属性类型进行自动装配，针对对象类型注入
        1，把service和dao对象创建，也就是添加创建对象的注解
        2，在service添加dao属性，然后在属性上方添加注解
        3，不需要添加set方法
    2, @Qualifier：根据属性的名称进行注入，针对对象类型注入
        1，需要和@AutoWired一起使用
        2，一个接口可能有多个实现类，不指明名称则不知道去何处找实现类
    3, @Resource：可以根据类型注入也可以根据名称注入，针对对象类型注入
        是javax包下的注解，官方建议使用Autowired和Qualifier
    4，@Value：注入普通类型

4，完全注解开发
    1，创建配置类，用来替代xml配置文件
    2，对配置类添加注解，让spring知道
    3，添加扫描注解让spring知道扫描范围


=======================


AOP(Aspect Oriented Programming)面向切面编程
    是通过预编译方式和运行期间动态代理实现程序功能的一种技术，aop可以对业务逻辑的各个部分隔离，从而降低耦合
    通俗：不修改源代码的方式添加新的功能，即把需要的功能单独写模块，然后配置到主干功能中去
1，AOP底层原理：
    1，AOP底层使用动态代理
    (1)两种情况的动态代理：
        a，有接口情况，使用JDK的动态代理
            1，创建UserDao接口实现代理对象
            2，增强类中的方法

        b，没有接口的情况，使用CGLIB的动态代理
            1，创建当前类子类的一个代理对象
            2，用代理对象增强了方法

2，AOP术语
    1，连接点
        类中哪些方法可以被增强，这些方法就叫做连接点
    2，切入点
        实际真正增强的方法称为接入点
    3，通知(增强)
        (1)实际被增强的逻辑部分被称为增强
        (2)通知的多种类型：
            1，前置通知：方法之前执行
            2，后置通知：方法之后执行
            3，环绕通知：方法前后都执行
            4，异常通知：方法出现异常就会执行
            5，最终通知：不管有没有异常，永远都会执行
    4，切面
        (1)是动作，将通知应用到切入点的过程

3，AspectJ：不是Spring的组成部分，一个独立的AOP框架，一般把AspectJ和Spring框架一起使用进行AOP

4，AOP操作
    1，Spring框架一般基于AspectJ实现AOP操作
        (1)，基于XML配置文件实现
        (2)，基于注解实现
    2，在项目工程中引入aop依赖，再引入aspectJ依赖和其相关依赖
    3，切入点表达式：
        1，切入点表达式的作用：知道对哪个类里面的 哪个方法进行增强
        2，语法结构：
            execution([权限修饰符][返回类型][类的全路径][方法名称]([参数列表]))
            例子1：对test.spring.dao.BookDao中的add()方法进行增强
            execution(* void【可省】 test.spring.dao.BookDao.add(..))
            例子2：对类中的所有方法增强
            execution(* test.spring.dao.BookDao.*(..))
            例子3：对包中所有类所有方法增强
            execution(* test.spring.*.*(..))

    4，使用注解进行增强
        1，创建类，定义方法
        2，创建增强类(编写增强逻辑)
        3，进行通知的配置
            1，spring配置文件中开启注解扫描
            2，使用注解创建类和代理类对象
                即使用@Component等注解
            3，在增强类上面添加注解@Aspect
                或者说是代理类
            4，在spring配置文件中开启生成代理对象
                <aop:aspectj-autoproxy>
                配置不同类型的通知(5种)
                即，在增强类中，在作为增强类中添加注解，并使用切入点表达式
                执行顺序：
                1,切入点正确执行顺序
                    @Around前置部分
                    @Before方法
                    切入点
                    @Around后置部分
                    @After方法
                    @AfterReturning方法
                2，切入点抛出异常顺序
                    @Around前置部分
                    @Before方法
                    切入点
                    @AfterThrowing方法
                    @After方法
        5，公共切入点抽取：
        对相同的切入点抽取，也叫重用切入点，就是用@Pointcur注解声明，效果和static final string代替效果一样
        6，对多个代理类设置优先级
            1，在增强类上添加注解@Order，数字类型越小优先级越高
            多个代理是类似于包裹形式的存在，优先级越高就在越外层

    5，使用xml配置
        了解即可

    6，使用配置类开启AOP

=-=============================
JDBC Template：spring对jdbc的封装

1，准备工作
    1，添加依赖：jdbc，连接池相关，spring-jdbc
    2，配置文件中配置连接池
    3，配置jdbctemplate对象，注入DataSource
    4，配置文件开启扫描
    5，在service中注入dao，在dao中注入jdbc模板，jdbc模板实现对数据库的增删改查

2，jdbc操作
    1，对应数据库表创建实体类
    2，和dbutils操作类似
    3，增删改：update
    4，查一个数和对象：queryForObject
    5，查集合：query

3，jdbc批量操作
    1，批量添加
    2，批量修改
    3，批量删除
        batchUpdate


4，Spring事务
    1，搭建事务操作环境
        1，建表，添加记录
        2，创建service，搭建dao，完成创建和注入，
            service注入dao，dao注入jdbctemplate，jdbctemplate中注入datasource
        3，

    2，事务操作：
        1，事务一般添加到到javaEE三层结构两种的 service层(业务逻辑层)
        2，Spring进行事务操作：
            1，编程式事务管理，即通过代码实现，try-catch，手动commit，rollback等
                缺点，代码臃肿，开发不方便
            2，声明式事务管理，即通过配置来进行，一般开发中都使用声明式
                1，基于注解实现，一般都使用注解
                2，基于xml配置文件实现
        3，Spring进行声明式事务管理，底层使用AOP原理
        4，Spring事务管理API
            1，提供一个接口，代表事务管理器，该接口针对不同的框架提供了不同的实现类
    3，流程：
        1，在Spring配置文件中配置事务管理器
        2，在Spring中配置，开启事务注解
            1,在spring配置文件中引入名称空间
            2,开启事务注解
            3,在service类上或service类方法上添加事务注解@Transactional
                如果添加到类上，表示类中所有方法都添加了事务
                如果添加到方法上，则只是为方法添加了事务

    4，声明式事务管理的参数配置
        在@Transactional()中可以配置属性：
            1，propagation
                1，事务的传播行为:多事务方法进行直接调用，这个过程中事务是如何进行管理的
                    事务方法：对数据库表中数据进行变化的操作
                    事务的传播行为：有事务方法调没事务方法，没事务方法调有事务方法，事务方法调事务方法
                2，Spring中有其中事务传播行为：
                    1，required：如果有事务运行，则当前方法在该事务内运行，如果没有事务运行，则另外启动一个事务，在事务中运行
                    2，required_new：当前方法必须启动新事物，当前方法在新事物中运行，如果已经有了事务在执行，则该方法被挂起
                    3，supports：如果有事务在运行，当前方法就在该事务内运行，否则它可以不运行在事务中
                    4，not_supported：当前方法不应该运行在事务中，如果有运行事务，将该方法挂起
                    5，mandatory：当前方法必须运行在事务内部，不然就报错
                    6，never：当前方法可以运行在事务中，不然就报错
                    7，nested：如果有事务在运行，当前方法应该在这个事务的嵌套事务内运行，否则新启动一个事务运行
            2，isolation
                1，事务的隔离级别:
                    三个读问题：脏读，不可重复读，幻读
                    设置事务的隔离性解决该问题：通过隔离级别来处理
            3，timeout
                1，超时时间，事务需要在一定的时间内提交，如果不在规定时间提交，事务就会回滚
                默认值是-1，表示不会滚
                单位是秒，可自己设置
            4，readOnly
                1，是否只读，默认值为false，表示可读可写，设置为true之后只能读
            5，rollbackFor
                1，回滚:设置出现了哪些异常会导致回滚
            6，noRollbackFor
                1，不回滚：设置出现哪些异常不会回滚
            7，noRollbackForClassName

    5，XML配置文件也可以做到
        1，配置事务管理器
        2，配置通知
        3，配置切入点和切面

    6，完全注解开发实现声明式事务管理
        1，创建配置类，替代xml文件
        2，

Spring5新功能：
1，整个Spring5基于java8，同时兼容jdk9，删除了一些不建议的方法
2，自带了通用的日志封装
    移除了log4JConfigListener，建议使用Log4j2版本，如果要使用log4j需要降到4版本
    1，引入jar包
    2，创建log4j2.xml配置文件，配置文件的名字是固定的
3，支持Nullable注解


 */


}
