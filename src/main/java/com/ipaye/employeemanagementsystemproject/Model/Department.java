package com.ipaye.employeemanagementsystemproject.Model;

public class Department {

    String departmentName;
    private Department department;

    public Department(String departmentName){
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return  true;
        if(object == null || getClass() != object.getClass()) return false;
        Department department = (Department) object;
        return departmentName.equals(department.departmentName);
    }


}
