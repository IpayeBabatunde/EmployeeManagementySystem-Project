package com.ipaye.employeemanagementsystemproject.Model;

public class Employee {

    String firstName;
    String lastName;
    String email;
    String designation;

    public Employee(String firstName, String lastName, String email, String designation){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.designation = designation;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && email.equals(employee.email) && designation.equals(employee.designation);
    }

    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole(){
        return role;
    }


}
