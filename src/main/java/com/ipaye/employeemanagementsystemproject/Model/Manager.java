package com.ipaye.employeemanagementsystemproject.Model;

import java.util.ArrayList;
import java.util.Collection;

public class Manager {

    private Collection<Employee> employees = new ArrayList<>();

    public Collection<Employee> getEmployees(){
        return employees;
    }
    public void addEmployeeToTeam(Employee employee)  {

        employees.add(employee);
    }

    public void updatePerformance(Employee employee, PerformanceReview performanceReview) {
    employee.setPerformanceReview(performanceReview);

        if(!employees.contains(employee)){
        throw new IllegalArgumentException("Employee not in Manager's team");
    }

    }

    public void updateSalary(Employee employee, int amount) {

        if(employee != null){
            int currentSalary = employee.getSalary();

            employee.setSalary(currentSalary + amount);

        }else {
            System.out.println("Employee object is null");
        }
        if (employee == null){
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if(!employees.contains(employee)){
            throw new IllegalArgumentException("Employee not in Manager's team");
        }
    }
}
