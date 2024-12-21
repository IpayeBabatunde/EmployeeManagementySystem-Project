package com.ipaye.employeemanagementsystemproject.service;

import com.ipaye.employeemanagementsystemproject.Model.PerformanceReview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryManagementService {

    private final SalaryManagementRepository repository;
    private Map<Object, List<PerformanceReview>> reviews = new HashMap<>();

    public SalaryManagementService(SalaryManagementRepository repository) {
        this.repository=repository;
    }


    public Object setSalary(Object id, Object id1, double salary) {

      return salary;
    }

    public Object updateSalary(Object id, Object id1, double newSalary) {
        return newSalary;
    }

    public List<SalaryRecord> getSalaryHistory(Object id) {
        return repository.findSalaryHistory(id);
    }
}
