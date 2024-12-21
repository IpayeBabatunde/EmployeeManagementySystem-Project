package com.ipaye.employeemanagementsystemproject.service;

import com.ipaye.employeemanagementsystemproject.Model.PerformanceReview;

import java.util.List;

public interface PerformanceReviewRepository {

    List<PerformanceReview> findReviewsByEmployeeId(Object id);
}
