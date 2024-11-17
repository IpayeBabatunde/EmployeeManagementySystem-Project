package com.ipaye.employeemanagementsystemproject;

import com.ipaye.employeemanagementsystemproject.Model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    // TEST CASE 7
    @Test
    void UpdateEmployeePerformance(){
        Manager manager = new Manager();
        Employee employee = new Employee("Ipaye", "Babatunde", "Ipayebabs@gmail.com", "software engineer");

        manager.addEmployeeToTeam(employee);

        PerformanceReview performanceReview = new PerformanceReview("Excellent");
        manager.updatePerformance(employee, performanceReview);

        assertEquals("Excellent", employee.getPerformanceReview().getRating());
    }

    // TEST CASE 8
    @Test
    void updateEmployeeSalary(){
        Manager manager = new Manager();
        Employee employee = new Employee("Daniel", "James", "Djamesz@gamil.com", "data analyst");
        manager.addEmployeeToTeam(employee);

        manager.updateSalary(employee, 70000);
        assertEquals(70000, employee.getSalary());
    }

    // TEST CASE 9
    @Test
    void CannotUpdatePerformanceOfEmployeeNotInManagersTeam(){
        Manager manager = new Manager();
        Employee employee = new Employee("John", "Adu", "Johnadu@gmail.com", "data analyst");

        PerformanceReview performanceReview = new PerformanceReview("Average");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            manager.updatePerformance(employee, performanceReview);
        });
        assertEquals("Employee not in Manager's team", exception.getMessage());
    }

    // TEST CASE 10
    @Test
    void CannotUpdateSalaryOfEmployeeNotInManagersTeam(){
        Manager manager = new Manager();
        Employee employee = new Employee("Jack", "Bauer", "JackBauer@gmail.com", "Software Engineer");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            manager.updateSalary(employee, 70000);
        });
        assertEquals("Employee not in Manager's team", exception.getMessage());
    }

    // TEST CASE 11
   @Test
   void EnsureManagerCanOnlyUpdatePerformanceAndSalaryOfTheirTeam(){

        Manager manager = new Manager();
        Employee employee = new Employee("Smith", "Chloe", "SmithCl@gmail.com", "Data Analyst");
        Employee otherEmployee = new Employee("Jennifer", "Houston", "JenG12@gmail.com", "Software Analyst");

        manager.addEmployeeToTeam(employee);

        PerformanceReview performanceReview = new PerformanceReview("Good");

        manager.updatePerformance(employee, performanceReview);
        assertEquals("Good", employee.getPerformanceReview().getRating());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            manager.updatePerformance(otherEmployee, performanceReview);
        });

        assertEquals("Employee not in Manager's team", exception.getMessage());

        manager.updateSalary(employee, 70000);
        assertEquals(70000, employee.getSalary(), 0.0);

        exception = assertThrows(IllegalArgumentException.class, () ->{
            manager.updateSalary(otherEmployee, 70000);
        });
        assertEquals("Employee not in Manager's team", exception.getMessage());
    }

}
