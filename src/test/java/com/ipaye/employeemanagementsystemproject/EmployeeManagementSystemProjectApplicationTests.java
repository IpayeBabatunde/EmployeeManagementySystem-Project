package com.ipaye.employeemanagementsystemproject;

import com.ipaye.employeemanagementsystemproject.Model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeManagementSystemProjectApplicationTests {

    //TEST CASE 1
    @Test
    void AddANewEmployee() {
        Admin admin = new Admin();
        Employee employee = new Employee("John", "apostle", "JohnAp@gmail.com", "software engineer");

        admin.getEmployee().add(employee);
        assertTrue(admin.getEmployee().contains(employee));
    }

    //TEST CASE 2
    @Test
    void removeAnEmployee(){
        Admin admin = new Admin();
        Employee employee = new Employee("Susan", "janet", "susan007@gmail.com", "Data Analyst");

        admin.getEmployee().add(employee);
        admin.removeEmployee(employee);

        assertFalse(admin.getEmployee().contains(employee));
    }

    //TEST CASE 3
    @Test
    void AddNewDepartment(){
        Admin admin = new Admin();
        Department department = new Department("Banking & Finance");

        admin.getDepartment().add(department);
        assertTrue(admin.getDepartment().contains(department));

    }

    // TEST CASE 4
    @Test
    void RemoveADepartment(){
        Admin admin = new Admin();
        Department department = new Department("Accounting");

        admin.getDepartment().add(department);
        admin.removeDepartment(department);

        assertFalse(admin.getDepartment().contains(department));

    }

    // TEST CASE 5
    @Test
    void viewAllReports(){
        Admin admin = new Admin();
        Report report = new Report("80000", "Profit was Made");

        admin.addReport(report);
        List<Report> reports = admin.viewAllReports();
        assertTrue(reports.contains(report));

    }

    // TEST CASE 6
    @Test
    void AssignRoleToEmployee(){
        Admin admin = new Admin();
        Employee employee = new Employee("Lionel", "Messi", "LioMessi@gmail.com", "Software engineering");
        Role role = new Role("Director");

        admin.assignRole(employee, role);

        assertEquals(role, employee.getRole());



    }

}
