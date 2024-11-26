package com.ipaye.employeemanagementsystemproject;

import com.ipaye.employeemanagementsystemproject.Model.*;
import com.ipaye.employeemanagementsystemproject.Model.Employee;
import com.ipaye.employeemanagementsystemproject.Model.Manager;
import com.ipaye.employeemanagementsystemproject.service.*;
import com.ipaye.employeemanagementsystemproject.Model.Admin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
       admin.addDepartment(hrDepartment);

        // Try adding a duplicate department and expect an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            admin.addDepartment(hrDepartment); // Trying to add the same department again
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

       assertEquals("Senior Engineer", admin.getRoleByTitle("Senior Engineer").getTitle());
    }

    // TEST CASE 43
    @Test
    void RemoveDepartment(){
        Admin admin = new Admin();
        Department hrDepartment = new Department("HR");

        admin.addDepartment(hrDepartment);
        admin.removeDepartment(hrDepartment);

        assertNull(admin.getDepartmentByName("HR"));


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
        PerformanceReviewService reviewService =Mockito.mock(PerformanceReviewService.class);
        com.ipaye.employeemanagementsystemproject.service.Manager manager = new com.ipaye.employeemanagementsystemproject.service.Manager(1, "John Doe");
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "Jane Smith");
        PerformanceReview review = new PerformanceReview(employee.getId(), manager.getId(), 4, 5, 3);

        //Mocking the behavior of the service method
        Mockito.when(reviewService.addPerformanceReview(manager.getId(), employee.getId(), review)).thenReturn("Performance review added successfully");


        //Invoking the method and asserting the result
        String result =(String) reviewService.addPerformanceReview(manager.getId(), employee.getId(), review);
        assertEquals("Performance review added successfully", result);


    }

    // TEST CASE 50
    @Test
    void ManagerCannotAddAReviewForAnInvalidEmployee(){
        PerformanceReviewService reviewService = Mockito.mock(PerformanceReviewService.class);
        com.ipaye.employeemanagementsystemproject.service.Manager manager = new com.ipaye.employeemanagementsystemproject.service.Manager(1, "John Doe");
        int invalidEmployeeId = 999;
        PerformanceReview review = new PerformanceReview(invalidEmployeeId, manager.getId(), 4, 5,3);

        // Mocking the behavior when employee is not found
        Mockito.when(reviewService.addPerformanceReview(manager.getId(), invalidEmployeeId, review))
                .thenThrow(new EmployeeNotFoundException("Employee not found"));

        // Exception testing
        Exception exception = assertThrows(EmployeeNotFoundException.class, ()->{
            reviewService.addPerformanceReview(manager.getId(), invalidEmployeeId, review);
        });

        assertEquals("Employee not found", exception.getMessage());
    }


    //  TEST CASE 51
    @Test
    void ManagerCannotSubmitAReviewWithInvalidMetricRating(){
        PerformanceReviewService reviewService = Mockito.mock(PerformanceReviewService.class);
        com.ipaye.employeemanagementsystemproject.service.Manager manager = new com.ipaye.employeemanagementsystemproject.service.Manager(1, "John doe");
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "Jane smith");
        PerformanceReview invalidReview = new PerformanceReview(employee.getId(), manager.getId(), 6, -1, 4);
        //Mocking the behavior when invalid rating are provided
        Mockito.when(reviewService.addPerformanceReview(manager.getId(), employee.getId(), invalidReview))
                .thenThrow(new InvalidRatingException("Invalid Rating for performance metrics"));

        // Exception testing
        Exception exception = assertThrows(InvalidRatingException.class, () ->{
            reviewService.addPerformanceReview(manager.getId(), employee.getId(), invalidReview);
        });

        assertEquals("Invalid Rating for performance metrics", exception.getMessage());
    }

    // TEST CASE 52
    @Test
    void RetrievePerformanceForAnEmployee(){
        PerformanceReviewService reviewService =Mockito.mock(PerformanceReviewService.class);
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "Jane smith");
        com.ipaye.employeemanagementsystemproject.service.Manager manager = new com.ipaye.employeemanagementsystemproject.service.Manager(1, "John Doe");

        // Sample reviews for the employee
        PerformanceReview review1 = new PerformanceReview(employee.getId(), manager.getId(), 5, 4, 5);
        PerformanceReview review2 = new PerformanceReview(employee.getId(), manager.getId(), 4, 5, 5);

        List<PerformanceReview> reviews = Arrays.asList(review1, review2);

        //Mocking the retrieval of reviews
        Mockito.when(reviewService.getPerformanceReviews(employee.getId())).thenReturn(reviews);

        // verifying that the reviews are returned correctly
        List<PerformanceReview> result = reviewService.getPerformanceReviews(employee.getId());
        assertEquals(2, result.size());
        assertEquals(0, result.get(0).getCommunication());

    }


    // TEST CASE 53
    @Test
    void InvalidMetricScoreTest(){
        com.ipaye.employeemanagementsystemproject.service.Employee  employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "John Doe");
        com.ipaye.employeemanagementsystemproject.service.Manager manager = new com.ipaye.employeemanagementsystemproject.service.Manager(1, "Jane smith");

        List<PerformanceMetrics> metrics = List.of(
                new PerformanceMetrics("Communication", 6),
                new PerformanceMetrics("Technical Expertise", 5),
                new PerformanceMetrics("Team work", 3)
        );

            PerformanceReview review = new PerformanceReview(employee, manager, metrics, "Excellent Technical skills");

            Exception exception = assertThrows(IllegalArgumentException.class, () ->{
                review.addPerformanceReview(review);
            });

            assertEquals("Performance Score Must be btwn 1 and 5 for metric comm", exception.getMessage());
    }

    // TEST CASE 54
    @Test
    void missingMetricTest(){
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "John doe");
        com.ipaye.employeemanagementsystemproject.service.Manager manager = new com.ipaye.employeemanagementsystemproject.service.Manager(1, "jane smith");

        List<PerformanceMetrics> metrics = List.of(new PerformanceMetrics("Communication", 4));

        PerformanceReview review = new PerformanceReview(employee, manager, metrics, "Needs improvement in some areas");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            review.addPerformanceReview(review);
        });

        assertEquals("All Performance metrics must be provided", exception.getMessage());
    }



    // TEST CASE 55
    @Test
    void EmptyReviewCommentTest(){
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "John Doe");
        com.ipaye.employeemanagementsystemproject.service.Manager manager = new com.ipaye.employeemanagementsystemproject.service.Manager(1, "Jack Bauer");

        List<PerformanceMetrics> metrics = List.of(
                new PerformanceMetrics("Communication", 4),
                new PerformanceMetrics("Technical Expertise", 5),
                new PerformanceMetrics("Teamwork", 3)

        );

        PerformanceReview review = new PerformanceReview(employee, manager, metrics, "");

        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            review.addPerformanceReview(review);
        });

        assertEquals("Review comments cannot be empty", exception.getMessage());

    }

    //  TEST CASE 56
    @Test
    void AdminCanASalaryForAnEmployee(){
        SalaryManagementService salaryManagementService = Mockito.mock(SalaryManagementService.class);
        com.ipaye.employeemanagementsystemproject.service.Admin admin = new com.ipaye.employeemanagementsystemproject.service.Admin(1, "AdminUser");
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "Jack Bauer");
        double salary = 7000.0;

        // mock the behavior of the setting salary
        Mockito.when(salaryManagementService.setSalary(admin.getId(), employee.getId(), salary))
                .thenReturn("Salary set successfully");

        //  execute and assert
        String result =(String) salaryManagementService.setSalary(admin.getId(), employee.getId(), salary);
        assertEquals("Salary set successfully", result);

    }


    // TEST CASE 57
    @Test
    void AdminCanUpdateEmployeeSalary(){
        SalaryManagementService salaryManagementService = Mockito.mock(SalaryManagementService.class);
        com.ipaye.employeemanagementsystemproject.service.Admin admin = new com.ipaye.employeemanagementsystemproject.service.Admin(1, "AdminUser");
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "Jack Doe");
        double newSalary = 80000.0;

        // Mock the behavior of the updating salary
        Mockito.when(salaryManagementService.updateSalary(admin.getId(), employee.getId(), newSalary))
                .thenReturn("Salary updated successfully");

        // Execute and assert
        String result =(String) salaryManagementService.updateSalary(admin.getId(), employee.getId(), newSalary);
        assertEquals("Salary updated successfully", result);
    }

    // TEST CASE 58
    @Test
    void RetrieveSalaryHistoryForAnEmployee(){
        SalaryManagementService salaryManagementService = Mockito.mock(SalaryManagementService.class);
        com.ipaye.employeemanagementsystemproject.service.Employee employee = new com.ipaye.employeemanagementsystemproject.service.Employee(1, "Jane Austin");

        //Mock salary history
        SalaryRecord salaryRecord1 = new SalaryRecord(1, 700000, "2023-01-01");
        SalaryRecord salaryRecord2 = new SalaryRecord(1, 900000, "2024-01-08");
        List<SalaryRecord> salaryHistory = Arrays.asList(salaryRecord1, salaryRecord2);

        // Mocking the behavior to retrieve salary history
        Mockito.when(salaryManagementService.getSalaryHistory(employee.getId())).thenReturn(salaryHistory);

        // Execute and assert
        List<SalaryRecord> result = salaryManagementService.getSalaryHistory(employee.getId());
        assertEquals(2, result.size());
        assertEquals(700000, result.get(0).getSalary());

    }

    // TEST CASE 59
    @Test
    void GenerateEmployeePerformanceReport(){

        ReportingService reportingService = Mockito.mock(ReportingService.class);

        //Mock report data
        PerformanceReport performanceReport = new PerformanceReport(4.2, 3.7, 4.7);

        //mock the behavior for generating the report
        Mockito.when(reportingService.generateEmployeePerformanceReport()).thenReturn(performanceReport);


        // Execute the method and assert the result
        PerformanceReport result =(PerformanceReport) reportingService.generateEmployeePerformanceReport();
        assertNotNull(result);
        assertEquals(4.2, result.getAverageCommunication());
        assertEquals(3.7, result.getAverageTeamwork());
        assertEquals(4.7, result.getAverageProblemSolving());

    }

    // TEST CASE 60


    

}
