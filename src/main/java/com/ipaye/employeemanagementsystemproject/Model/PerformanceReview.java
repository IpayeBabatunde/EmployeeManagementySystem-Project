package com.ipaye.employeemanagementsystemproject.Model;

public class PerformanceReview {

    String overallPerformanceReview;

    public PerformanceReview(String overallPerformanceReview){
        this.overallPerformanceReview = overallPerformanceReview;
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
}
