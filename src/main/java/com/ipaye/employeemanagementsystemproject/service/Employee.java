package com.ipaye.employeemanagementsystemproject.service;

public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Object getId() {
        return id;
    }
}
