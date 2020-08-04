package com.test.spring5.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stu {

    private String[] cources;

    private List<String> list;

    private Map<String, String> map;

    private Set<String> set;

    private List<Course> courses;

    public Stu() {
    }

    public Stu(String[] cources, List<String> list, Map<String, String> map, Set<String> set) {
        this.cources = cources;
        this.list = list;
        this.map = map;
        this.set = set;
    }

    public String[] getCources() {
        return cources;
    }

    public void setCources(String[] cources) {
        this.cources = cources;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "cources=" + Arrays.toString(cources) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", courses=" + courses +
                '}';
    }
}
