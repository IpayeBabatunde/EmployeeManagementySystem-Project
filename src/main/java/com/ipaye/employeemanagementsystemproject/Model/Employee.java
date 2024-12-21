package com.ipaye.employeemanagementsystemproject.Model;

import java.util.HashMap;
import java.util.Map;

public class Employee {

    String firstName;
    String lastName;
    String email;
    String designation;

    String department;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public void setDesignation(String designation) {
        this.designation=designation;
    }

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

    public void setRoles(Role role) {
        this.role = role;
    }
    public Role getRole(){

        return role;
    }

    private PerformanceReview performanceReview;

    public void setPerformanceReview(PerformanceReview performanceReview){
        this.performanceReview = performanceReview;
    }

    public PerformanceReview getPerformanceReview(){
        return performanceReview;
    }

    private int salary;

    public Employee(int salary){
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public String getEmail() {
        return email;

    }

    private int id;
    public int getId() {
        return id;
    }

    private Map<Integer, Employee> employeeMap = new HashMap<>();
    public boolean update(Employee employee) {
        if(employee == null || !employeeMap.containsKey(employee.getId())){
            return false;
        }

        employeeMap.put(employee.getId(), employee);
        return true;
    }


    public String getName() {
        return firstName;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

//    public void setRole(String newRole) {
//        this.role = role;
//    }

    public void setRole(Role role) {
        this.role = role;
    }
}
