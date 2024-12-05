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

    public void assignRole(Employee employee, Role role) {
            employee.setRole(role);
    }
}
