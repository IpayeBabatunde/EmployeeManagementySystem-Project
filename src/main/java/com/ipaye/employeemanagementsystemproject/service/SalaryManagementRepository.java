package com.ipaye.employeemanagementsystemproject.service;

import java.util.List;

public interface SalaryManagementRepository {
    List<SalaryRecord> findSalaryHistory(Object id);
}
