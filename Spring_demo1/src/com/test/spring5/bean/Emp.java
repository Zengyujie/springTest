package com.test.spring5.bean;

public class Emp {
    private String ename;
    private String gender;
    private Dept dept;

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getEname() {
        return ename;
    }

    public String getGender() {
        return gender;
    }

    public Dept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "ename='" + ename + '\'' +
                ", gender='" + gender + '\'' +
                ", dept=" + dept +
                '}';
    }
}
