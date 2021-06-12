package com.lishuai.entity;

import lombok.Data;

@Data
public class Student {

    public Student(Integer id, String name, String telephone) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
    }

    private Integer id;
    private String name;
    private String telephone;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
