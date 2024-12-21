package com.ipaye.employeemanagementsystemproject.Model;

import ch.qos.logback.core.BasicStatusManager;

import java.util.*;

public class Admin {

    private Collection<Employee> employees = new ArrayList<>();
    private final Set<String> departmentNames = new HashSet<>();

    public Collection <Employee> getEmployee() {

        return employees;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    private Collection <Role> roles = new ArrayList<>();
    private Collection <Department> departments = new ArrayList<>();
    public Collection <Department> getDepartment() {
        return departments;
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }


    private List<Report> reports;

   // private List<Role> roles;
    private String department;
    public Admin(){

        this.reports = new ArrayList<>();
        this.roles = new ArrayList<>();
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



    private final Map<String, Department> departmentMap = new HashMap<>();

    public Department getDepartmentByName(String departmentName) {

        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }

        Department department = departmentMap.get(departmentName.toLowerCase());
        if (department == null) {
            throw new IllegalStateException("No department found with name: " + departmentName);
        }else {

            return department;
        }
    }

    public void addDepartment(Department department) {
        if (department == null || department.getName() == null || department.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Department already exists");
        }

        String departmentName = department.getName().trim().toLowerCase();  // Normalize the name (lowercase, trimmed)

        // Check if the department already exists
        if (!departmentNames.add(departmentName)) {
            throw new IllegalArgumentException("Department already exists");
        }
    }

    public void addDepartments(Department department) {
      departments.add(department);
    }



    public void addRole(Role role) {

        if(role != null && !roles.contains(role)){
            roles.add(role);
        }
    }

    public List<Role> getRoles(){
        return (List<Role>) roles;
    }

    public Role getRoleByTitle(String roleTitle) {
        if(roleTitle == null || roleTitle.isEmpty()){
            return null;
        }
        for(Role role : roles){
            if(role.getName().equalsIgnoreCase(roleTitle)){
                return role;
            }
        }
        return null;
    }

    public void addRoles(Role executiveRole) {
        if(roles == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        for (Role existingRole : roles){
            if(existingRole.getName().equalsIgnoreCase(existingRole.getName())){
                throw new IllegalArgumentException("Role already exists");
            }
        }
    }

    public void updateDepartment(String newDepartment) {
        if(newDepartment == null || newDepartment.trim().isEmpty()){
            throw new IllegalArgumentException("New Department cannot be null or empty");
        }
        this.department = newDepartment;

    }

    public Department getDepartmentByNames(String departmentName) {

        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }
        for (Department department : departments) {
            if (department.getName().equalsIgnoreCase(departmentName)) {
                return department;
            }
        }
        throw new IllegalStateException("No department found with name: " + departmentName);
    }

    String updateRole;
    public void updateRoleTitle(String roleTitle) {

        if(roleTitle == null || roleTitle.trim().isEmpty()){
            throw new IllegalArgumentException("New Department cannot be null or empty");
        }
        this.updateRole = roleTitle;
    }

    public Role getRoleByTitles(String roleTitle) {

        if (roleTitle == null || roleTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase(roleTitle)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Department name cannot be null or empty.");
    }


    public void removeRole(String roleName) {
        if(roleName == null ){
            System.out.println("Role does not exist");
            return;
        }
        if (employees.remove(roleName)){
            System.out.println("Role Deleted Successfully: " +
                    roleName);
        }else {
            throw new IllegalArgumentException( "Role does not exist");
        }


    }

    public void removeDepartments(String departmentName) {

        if(departmentName == null ){
            System.out.println("Department does not exist");
            return;
        }
        if (employees.remove(departmentName)){
            System.out.println("Department Deleted Successfully: " +
                    departmentName);
        }else {
            throw new IllegalArgumentException( "Department does not exist");
        }


    }

    public void removeRoles(Role executiveRole) {
        roles.remove(executiveRole);
    }


    public List<Department> getAllDepartment() {
        return (List<Department>) departments;

    }

    public List<Role> getAllRoles() {

        return (List<Role>) roles;
    }

    public void addMoreDepartments(Department moreDepartments) {
        if(moreDepartments != null && !roles.contains(moreDepartments)){

        }
    }
}


