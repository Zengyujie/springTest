package com.test.spring5.beanLife;

public class Orders {
    private String oname;

    public Orders() {
        System.out.println("order的无参构造");
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        System.out.println("调用set方法设置oname");
        this.oname = oname;
    }

    //创建一个初始化方法
    public void initMethod(){
        System.out.println("执行初始化方法");
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oname='" + oname + '\'' +
                '}';
    }


    //创建销毁方法
    public void destoryMethod(){
        System.out.println("销毁order对象");
    }
}
