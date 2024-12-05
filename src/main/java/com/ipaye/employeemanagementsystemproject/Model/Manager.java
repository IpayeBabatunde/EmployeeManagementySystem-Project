package com.ipaye.employeemanagementsystemproject.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    public void createEmployee(Employee employee) {
        if(employees.contains(employee)){
            throw new IllegalArgumentException("Employee with the same email already exists");
        }
        employees.add(employee);
        
        


    }

    public Collection<Employee> getAllEmployees() {

        return employees;
    }

    public void updateEmployee(Employee employee) {
        if(employee == null || !employeeExists(employee.getId())) {
            throw new IllegalArgumentException("Cannot update a non-existent employee");
        }


    }
    private boolean employeeExists(int id){
        return employees.contains(id);
    }

    public Employee getEmployeeByEmail(String email) {
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        Employee employee = findEmployeeByEmail(email);
        
        if(employee == null){
            throw new IllegalArgumentException("Employee with the email given does not exist");
        }
        return employee;
    }

    private Employee findEmployeeByEmail(String email) {
        if(email == null || email.isEmpty()){
            throw  new IllegalArgumentException("Email Cannot be null");

        }
        for(Employee employee : employees){
            if(employee.getEmail().equalsIgnoreCase(email)){
                return employee;
            }
        }
        return null;
    }

    public void deleteEmployee(Employee employee) {

        if(employee == null ){
            System.out.println("Cannot Delete a null employee");
            return;
        }
        if (employees.remove(employee)){
            System.out.println("Employee Deleted Successfully: " +
                    employee);
        }else {
            throw new IllegalArgumentException( "Employee not found");
        }

    }

    public void updateEmployees(Employee employee) {
        if(employee == null ){
            System.out.println("Employee cannot be null");
        }

    }

    public void deleteEmployees(Employee employee) {


    }

    public List<Employee> searchEmployeeByName(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if input is null or empty
        }
        return employees.stream()
                .filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void updateEmployeeFields(String email, String firstName, String lastName, String designation) {
        for (Employee employee : employees) {
            if (employee.getEmail().equalsIgnoreCase(email)) {
                if (firstName != null && !firstName.isEmpty()) {
                    employee.setFirstName(firstName);
                }
                if (lastName != null && !lastName.isEmpty()) {
                    employee.setLastName(lastName);
                }
                if (designation != null && !designation.isEmpty()) {
                    employee.setDesignation(designation);
                }
                return; // Exit after updating the employee
            }
        }
        throw new IllegalArgumentException("Employee with email " + email + " not found.");
    }


    public void bulkDeleteEmployees(List<String> emailsToDelete) {
        if(emailsToDelete == null || emailsToDelete.isEmpty()){
            throw new IllegalArgumentException("The List of emails to delete cannot be null");
        }

        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()){
            Employee employee = iterator.next();
            if(emailsToDelete.contains(employee.getEmail())){
                iterator.remove();
            }
        }
    }

    public void createEmployees(Employee invalidEmailEmployee) {
        if (invalidEmailEmployee == null || !isValidEmail(invalidEmailEmployee.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
      
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public void assignDepartment(String email, String department) {
        if (email == null || department == null || email.isEmpty() || department.isEmpty()) {
            throw new IllegalArgumentException("Invalid Department");

        }



        for (Employee employee : employees) {
            if (employee.getEmail().equalsIgnoreCase(email)) {
                employee.setDepartment(department);
                return; // Department assigned successfully
            }
        }

        throw new IllegalArgumentException("Employee does not exist");
    }


    public void assignRole(String email, Role newRole) {
        if (email == null || email.isEmpty() || newRole == null) {
            throw new IllegalArgumentException("Invalid Role" );
        }


        for (Employee employee : employees) {
            if (employee.getEmail().equalsIgnoreCase(email)) {
                employee.setRole(newRole);
                return; // Successfully assigned role
            }
        }

        throw new IllegalArgumentException("Employee does not exist");
    }

    public List<Employee> getEmployeesByDepartment(String hrDepartment) {
        if (hrDepartment == null) {
            throw new IllegalArgumentException("Department cannot be null.");
        }

        return employees.stream()
                .filter(employee -> hrDepartment.equals(employee.getDepartment()))
                .collect(Collectors.toList());
    }


    public List<Employee> getEmployeesByRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }

        return employees.stream()
                .filter(employee -> role.equals(employee.getRole()))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeByDepartmentAndRole(String department, Role role) {
        if (department == null || department.isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty.");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }

        return employees.stream()
                .filter(employee -> department.equalsIgnoreCase(employee.getDepartment()) && role.equals(employee.getRole()))
                .collect(Collectors.toList());
    }


}



//    public void assignDepartments(String email, Department department) {
//        if (email == null || email.isEmpty()) {
//            throw new IllegalArgumentException("Invalid Department");
//
//        }
//        for (Employee employee : employees) {
//            if (employee.getEmail().equalsIgnoreCase(email)) {
//
//                return; // Department assigned successfully
//            }
//        }
//        throw new IllegalArgumentException("Invalid Department");
//
//    }


