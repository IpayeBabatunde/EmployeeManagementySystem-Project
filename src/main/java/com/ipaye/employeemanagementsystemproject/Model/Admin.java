package com.ipaye.employeemanagementsystemproject.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Admin {

    private Collection<Employee> employees = new ArrayList<>();

    public Collection <Employee> getEmployee() {

        return employees;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    private Collection <Department> departments = new ArrayList<>();
    public Collection <Department> getDepartment() {
        return departments;
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }

    private List<Report> reports;

    public Admin(){
        this.reports = new ArrayList<>();
    }
    public void addReport(Report report) {
        reports.add(report);

    }
    public List<Report> viewAllReports(){
        return new ArrayList<>(reports);
    }

//    public void assignRole(Employee employee, String role) {
//            employee.setRole(role);
//    }

    public void assignRole(Employee employee, Role role) {
        employee.setRole(role);
    }

    public void addEmployee(Employee employee) {

        employees.add(employee);
    }

    public void assignRole(String email, Role role) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }

        for (Employee employee : employees) {
            if (employee.getEmail().equalsIgnoreCase(email)) {
                employee.setRole(role); // Make sure this is implemented in the Employee class
                return;
            }
        }

        throw new IllegalArgumentException("Employee with email " + email + " not found.");
    }

    public void assignRoles(Employee employee, Role role) {
        employee.setRole(role);


    }
}
