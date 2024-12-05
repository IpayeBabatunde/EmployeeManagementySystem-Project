package com.ipaye.employeemanagementsystemproject.Model;

import com.ipaye.employeemanagementsystemproject.service.Employee;
import com.ipaye.employeemanagementsystemproject.service.Manager;
import com.ipaye.employeemanagementsystemproject.service.PerformanceMetrics;

import java.util.List;

public class PerformanceReview {

    String overallPerformanceReview;
    int communication;

    public PerformanceReview(String overallPerformanceReview){
        this.overallPerformanceReview = overallPerformanceReview;
    }

    public PerformanceReview(Object id, Object id1, int i, int i1, int i2) {

    }

    public PerformanceReview(Employee employee, Manager manager, List<PerformanceMetrics> metrics, String rating) {

    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || getClass() != object.getClass()) return false;
        PerformanceReview performanceReview = (PerformanceReview) object;
        return overallPerformanceReview.equals(performanceReview.overallPerformanceReview);
    }

    public String getRating() {
        return overallPerformanceReview;
    }

    public int getCommunication() {
        return communication;
    }

    public void addPerformanceReview(PerformanceReview review) {

    }
}
