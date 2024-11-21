package com.ipaye.employeemanagementsystemproject.Model;

public class Department {

//    private String name;

    String departmentName;
    private Department department;



    public Department(String departmentName){
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }

        this.departmentName = departmentName;
    }

    public void setName(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }
        this.departmentName = departmentName;
    }
    public String getName() {
        return departmentName;


    }

    @Override
    public boolean equals(Object object){
        if(this == object) return  true;
        if(object == null || getClass() != object.getClass()) return false;
        Department department = (Department) object;
        return departmentName.equals(department.departmentName);
    }






}
