package com.ipaye.employeemanagementsystemproject;

import com.ipaye.employeemanagementsystemproject.Model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.LinkOption;
import java.util.Arrays;
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
        Employee employee = new Employee("John", "Doe", "John.doe@gmail.com", "Software Engineer");
        Role role = new Role("HR");

        admin.assignRoles(employee, role);

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

    // TEST CASE 12
    @Test
    void createNewEmployeeRecord(){
        Manager manager = new Manager();
        Employee employee =  new Employee("John", "Stones", "Johnst@gmail.com", "frontend developer");
        manager.createEmployee(employee);

        assertTrue(manager.getAllEmployees().contains(employee));

    }

    // TEST CASE 13
    @Test
    void updateAnExistingEmployeeRecord(){
        Manager manager = new Manager();
        Employee employee = new Employee("Oscar", "Bob", "Oscarbob@gmail.com", "data analyst");
        manager.createEmployee(employee);

        employee.setSalary(70000);
        manager.updateEmployees(employee);

        assertEquals(70000, manager.getEmployeeByEmail("Oscarbob@gmail.com").getSalary(), 0.0);
    }


    // TEST CASE 14
    @Test
    void DeleteAnEmployeeRecord(){
        Manager manager = new Manager();
        Employee employee = new Employee("Sandra", "Tinashe", "SandraT@gmail.com", "Software engineer");
        manager.createEmployee(employee);

        manager.deleteEmployee(employee);

        assertFalse(manager.getAllEmployees().contains(employee));
    }

    // TEST CASE 15
    @Test
    void GetEmployeeByEmail(){
        Manager manager = new Manager();
        Employee employee = new Employee("Houston", "Rose", "Hrose@yopmail.com", "backend operator");
        manager.createEmployee(employee);

        Employee retrievedEmployee = manager.getEmployeeByEmail("Hrose@yopmail.com");

        assertEquals(employee, retrievedEmployee);

    }

    // TEST CASE 16
    @Test
    void CannotUpdateNonExistentEmployee(){
        Manager manager = new Manager();
        Employee nonExistentEmployee = new Employee("Non", "Existent", "non.existemt@gmail.com", "null");

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            manager.updateEmployee(nonExistentEmployee);
        });

        assertEquals("Cannot update a non-existent employee", exception.getMessage());
    }

    // TEST CASE 17
    @Test
    void CannotDeleteNonExistentEmployee(){
        Manager manager = new Manager();
        Employee nonExistentEmployee = new Employee("John", "Doe", "jd@gmail.com", "frontend engineer");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            manager.deleteEmployee(nonExistentEmployee);
        });

        assertEquals("Employee not found", exception.getMessage());
    }

    // TEST CASE 18
    @Test
    void ListAllEmployees(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("Akin", "Bello", "AkinB@ymail.com", "data engineer");
        Employee employee2 = new Employee("Yaya", "Bello", "YayaB@gmail.com", "Data analyst");

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);

        List<Employee> employees =(List<Employee>) manager.getAllEmployees();
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
        assertEquals(2, employees.size());

    }

    // TEST CASE 19
    @Test
    void preventDuplicateEmployeeCreation(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("Akin", "Bello", "AkinB@ymail.com", "data engineer");
        Employee employee2 = new Employee("Akin", "Bello", "AkinB@ymail.com", "data engineer");

        manager.createEmployee(employee1);

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            manager.createEmployee(employee2);
        });
        assertEquals("Employee with the same email already exists", exception.getMessage());
    }

    // TEST CASE 20
    @Test
    void searchEmployeeByName(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("John", "Rowe", "Jrowe@gmail.com", "Software analyst");
        Employee employee2 = new Employee("Mary", "Banks", "MaryBanks@gmail.com", "data engineer");

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);

        List<Employee> result = manager.searchEmployeeByName("Mary");

        assertEquals(1, result.size());
        assertTrue(result.contains(employee2));
    }

    // TEST CASE 21
    @Test
    void UpdateOnlySpecificFieldOfAnEmployeeRecord(){
        Manager manager = new Manager();
        Employee employee = new Employee("John", "Rowe", "Jrowe@gmail.com", "Software analyst");

        manager.createEmployee(employee);

        // only update the designation without changing name or email
        manager.updateEmployeeFields("Jrowe@gmail.com", "Null", "null", "Data Engineer");

        Employee updatedEmployee = manager.getEmployeeByEmail("Jrowe@gmail.com");
        assertEquals("Data Engineer", updatedEmployee.getDesignation());
        assertEquals("Jrowe@gmail.com", updatedEmployee.getEmail());

    }

    // TEST CASE 22
    @Test
    void deleteBulkEmployees(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("John", "Rowe", "Jrowe@gmail.com", "Software analyst");
        Employee employee2 = new Employee("Mary", "Banks", "MaryBanks@gmail.com", "data engineer");

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);

        List<String> emailsToDelete =Arrays.asList("Jrowe@gmail.com", "MaryBanks@gmail.com");
        manager.bulkDeleteEmployees(emailsToDelete);

        assertEquals(0, manager.getAllEmployees().size());
    }

    // TEST CASE 23
    @Test
    void ValidateEmployeeData(){
        Manager manager = new Manager();
        Employee invalidEmailEmployee = new Employee("John", "Rowe", "invalid-email", "data engineer");

        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            manager.createEmployees(invalidEmailEmployee);
        });
        assertEquals("Invalid email format", exception.getMessage());
    }

    // TEST CASE 24
    @Test
    void handleEmptyEmployeeList(){
        Manager manager = new Manager();
        List<Employee> employees =(List<Employee>) manager.getAllEmployees();

        assertTrue(employees.isEmpty());
    }

    // TEST CASE 25
    @Test
    void assignDepartmentToEmployee(){
        Manager manager = new Manager();
        Employee employee = new Employee("John", "Rowe", "Jrowe@gmail.com", "Software analyst");

        String hrDepartment =new Department("HR").toString();

        manager.createEmployee(employee);
        manager.assignDepartment(employee.getEmail(), hrDepartment);

        assertEquals(hrDepartment, employee.getDepartment());
    }


    // TEST CASE 26
    @Test
    void updateEmployeesRole(){
        Manager manager = new Manager();
        Employee employee = new Employee("John", "Rowe", "Jrowe@gmail.com", "Software analyst");
        Role softwareAnalyst = new Role("Software Analyst");
        Role dataEngineer = new Role("Data Engineer");



        manager.createEmployee(employee);
        manager.assignRole(employee.getEmail(), softwareAnalyst);
        manager.assignRole(employee.getEmail(), dataEngineer);

    }

    // TEST CASE 27
    @Test
    void CannotAssignNonExistentDepartment(){
        Manager manager = new Manager();
        Employee employee = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");

        manager.createEmployees(employee);

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            manager.assignDepartment(employee.getEmail(), null);
        });

        assertEquals("Invalid Department", exception.getMessage());

    }

    // TEST CASE 28
    @Test
    void cannotAssignNonExistenceRole(){
        Manager manager = new Manager();
        Employee employee = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");

        manager.createEmployees(employee);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.assignRole(employee.getEmail(), null);
        });

        assertEquals("Invalid Role", exception.getMessage());
    }

    // TEST CASE 29
    @Test
    void ListEmployeesByDepartment(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");
        Employee employee2 = new Employee("Luke", "Sampson", "LukeSam@gmail.com", "data analyst");

        String hrDepartment =new Department("HR").toString();
        String engineeringDepartment =new Department("Engineering").toString();

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);
        manager.assignDepartment(employee1.getEmail(), hrDepartment);
        manager.assignDepartment(employee2.getEmail(), engineeringDepartment);

        List<Employee> hrEmployees = manager.getEmployeesByDepartment(hrDepartment);

        assertEquals(1, hrEmployees.size());
        assertTrue(hrEmployees.contains(employee1));

        }

      //   TEST CASE 30
        @Test
        void ListEmployeesByRole(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");
        Employee employee2 = new Employee("Luke", "Sampson", "LukeSam@gmail.com", "data analyst");

        Role managerRole = new Role("Manager");
        Role engineerRole = new Role("Engineer");

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);
        manager.assignRole(employee1.getEmail(), managerRole);
        manager.assignRole(employee2.getEmail(), engineerRole);

        List<Employee> managers = manager.getEmployeesByRole(managerRole);

        assertEquals(1, managers.size());
        assertTrue(managers.contains(employee1));



        }

        // TEST CASE 31
        @Test
        void assignMultipleEmployeesToTheSameDepartment(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");
        Employee employee2 = new Employee("Luke", "Sampson", "LukeSam@gmail.com", "data analyst");

        String HrDepartment =new Department("HR").toString();

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);
        manager.assignDepartment(employee1.getEmail(), HrDepartment);
        manager.assignDepartment(employee2.getEmail(), HrDepartment);

        List<Employee> hrEmployees = manager.getEmployeesByDepartment(HrDepartment);
        assertEquals(2, hrEmployees.size());
        assertTrue(hrEmployees.contains(employee1));
        assertTrue(hrEmployees.contains(employee2));
    }


    // TEST CASE 32
    @Test
    void ReassignMultipleEmployeeToNewDepartment(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");
        Employee employee2 = new Employee("Luke", "Sampson", "LukeSam@gmail.com", "data analyst");
        String engineeringDepartment =new Department("Engineering").toString();
        String hrDepartment =new Department("HR").toString();

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);
        manager.assignDepartment(employee1.getEmail(), engineeringDepartment);
        manager.assignDepartment(employee2.getEmail(), engineeringDepartment);

        // Reassign both employees to HR
        manager.assignDepartment(employee1.getEmail(), hrDepartment);
        manager.assignDepartment(employee2.getEmail(), hrDepartment);

        List<Employee> hrEmployees = manager.getEmployeesByDepartment(hrDepartment);

        assertEquals(2, hrEmployees.size());
        assertTrue(hrEmployees.contains(employee1));
        assertTrue(hrEmployees.contains(employee2));
    }

    // TEST CASE 33
    @Test
    void ReassignEmployeeToANewRole(){
        Manager manager = new Manager();
        Employee employee = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");
        Role juniorEngineer = new Role("Junior Engineer");
        Role seniorEngineer = new Role("Senior Engineer");

        manager.createEmployee(employee);
        manager.assignRole(employee.getEmail(), juniorEngineer);

        //Reassign to a new role
        manager.assignRole(employee.getEmail(), seniorEngineer);

        assertEquals(seniorEngineer, employee.getRole());
    }

    // TEST CASE 34
    @Test
    void AttemptToAssignEmployeeToDepartmentWhenNotCreated(){
        Manager manager = new Manager();
        String hrDepartment =new Department("HR").toString();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            manager.assignDepartment("nonexistent@gmail.com", hrDepartment);
        });

        assertEquals("Employee does not exist"  , exception.getMessage());


    }

    // TEST CASE 35
    @Test
    void AttemptToAssignEmployeeToRoleWhenNotCreated(){
        Manager manager = new Manager();
        Role engineerRole = new Role("Engineer");

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            manager.assignRole("nonexistent@gmail.com", engineerRole);
        });
        assertEquals("Employee does not exist", exception.getMessage());

    }

    // TEST CASE 36
    @Test
    void ListEmployeesWhenNoOneIsAssignedToADepartment(){
        Manager manager = new Manager();
        String engineeringDepartment =new Department("Engineering").toString();

        List<Employee> engineeringEmployees = manager.getEmployeesByDepartment(engineeringDepartment);

        assertTrue(engineeringEmployees.isEmpty());
    }

    // TEST CASE 37
    @Test
    void EnsureEmployeesCanBeListedByDepartmentAndRole(){
        Manager manager = new Manager();
        Employee employee1 = new Employee("John", "Stones", "Johnst@gmail.com", "software engineer");
        Employee employee2 = new Employee("Luke", "Sampson", "LukeSam@gmail.com", "data analyst");
        String hrDepartment =new Department("HR").toString();
        String engineeringDepartment =new Department("Engineering").toString();

        Role supervisorRole = new Role("Supervisor");
        Role engineerRole = new Role("Engineer");

        manager.createEmployee(employee1);
        manager.createEmployee(employee2);
        manager.assignDepartment(employee1.getEmail(), hrDepartment);
        manager.assignRole(employee1.getEmail(), supervisorRole);
        manager.assignDepartment(employee2.getEmail(), engineeringDepartment);
        manager.assignRole(employee2.getEmail(), engineerRole);

        // List by department and role
        List<Employee> hrSupervisor = manager.getEmployeeByDepartmentAndRole(hrDepartment, supervisorRole);

        assertEquals(1, hrSupervisor.size());
        assertTrue(hrSupervisor.contains(employee1));
    }

    // TEST CASE 38

    @Test
    void preventAddingDuplicateDepartment(){
        Admin admin = new Admin();
        Department hrDepartment = new Department("hr");

        // Add the first department
       admin.addDepartments(hrDepartment);

        // Try adding a duplicate department and expect an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            admin.addDepartment(new Department("hr")); // Trying to add the same department again
        });

        // Check the exception message
        assertEquals("Department already exists", exception.getMessage());
    }


    // TEST CASE 39
    @Test
    void AddNewRole(){
        Admin admin = new Admin();
        Role executiveRole = new Role("Executive");

        admin.addRole(executiveRole);

        assertEquals(executiveRole, admin.getRoleByTitle("Executive"));

    }


    // TEST CASE 40
    @Test
    void preventAddingDuplicateRole(){
        Admin admin = new Admin();
        Role executiveRole = new Role("Executive");

        admin.addRole(executiveRole);

        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            admin.addRoles(new Role("Executive"));
        });

        assertEquals("Role already exists", exception.getMessage());

    }


    // TEST CASE 41
    @Test
    void UpdateDepartmentName(){
        Admin admin = new Admin();
        Department hrDepartment = new Department("HR");

        admin.addDepartments(hrDepartment);
        admin.updateDepartment("HR");
        assertEquals("HR", admin.getDepartmentByNames("HR").getName());
    }

    // TEST CASE 42
    @Test
    void updateRoleTitle(){
        Admin admin = new Admin();
        Role engineerRole = new Role("Engineer");

        admin.addRole(engineerRole);
        admin.updateRoleTitle("Senior Engineer");

       assertEquals("Senior Engineer", admin.getRoleByTitles("Senior Engineer").getTitle());
    }

    // TEST CASE 43
    @Test
    void RemoveDepartment(){
        Admin admin = new Admin();
        Department hrDepartment = new Department("HR");

        admin.addDepartment(hrDepartment);
        admin.removeDepartment(hrDepartment);

        assertNull(admin.getDepartmentByNames("HR"));


    }

    // TEST CASE 44
    @Test
    void RemoveRole(){
        Admin admin = new Admin();
        Role executiveRole = new Role("Executive");

        admin.addRole(executiveRole);
        admin.removeRoles(executiveRole);

        assertNull(admin.getRoleByTitle("Executive"));
    }


    //TEST CASE 45
    @Test
    void PreventRemovingNonExistentDepartment(){
        Admin admin = new Admin();

        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            admin.removeDepartments("NonExistentDepartment");
        });

        assertEquals("Department does not exist", exception.getMessage());

    }

    // TEST CASE 46
    @Test
    void PreventRemovingNonExistingRole(){
        Admin admin = new Admin();

        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            admin.removeRole("NonExistingRole");
        });
        assertEquals("Role does not exist", exception.getMessage());
    }

    // TEST CASE 47
    @Test
    void ListAllDepartment(){
        Admin admin = new Admin();
        Department hrDepartment = new Department("HR");
        Department dataDepartment = new Department("DATA");

        admin.addDepartments(hrDepartment);
        admin.addDepartments(dataDepartment);

        List<Department> departments = admin.getAllDepartment();

        assertEquals(2, departments.size());
        assertTrue(departments.contains(hrDepartment));
        assertTrue(departments.contains(dataDepartment));
    }

    // TEST CASE 48
    @Test
    void getAllRoles(){
        Admin admin = new Admin();
        Role executiveRole = new Role("Executive");
        Role directorRole = new Role("Director");

        admin.addRole(executiveRole);
        admin.addRole(directorRole);

        List<Role> roles = admin.getAllRoles();

        assertEquals(2, roles.size());
        assertTrue(roles.contains(executiveRole));
        assertTrue(roles.contains(directorRole));
        
    }

     // TEST CASE 49
    @Test
    void ManagerCanAddAPerformanceReviewForAnEmployee(){



    }


}
