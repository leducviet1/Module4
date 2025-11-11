package com.example.myfirststringmvc.models;

public class Student {
    String code;
    String name;
    Float gpa;
    public Student() {}
    public Student(String code, String name, Float gpa) {
        this.code = code;
        this.name = name;
        this.gpa = gpa;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }
}
