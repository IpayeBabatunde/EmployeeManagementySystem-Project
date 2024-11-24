package com.ipaye.employeemanagementsystemproject.service;

import com.ipaye.employeemanagementsystemproject.Model.PerformanceReview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformanceReviewService {

    public void addPerformanceReview(){

    }

    private final PerformanceReviewRepository repository;
    private Map<Object, List<PerformanceReview>> reviews = new HashMap<>();

    public PerformanceReviewService(PerformanceReviewRepository repository){
        this.repository = repository;
    }

    public Object addPerformanceReview(Object id, Object id1, PerformanceReview review) {
        reviews.putIfAbsent(id, new ArrayList<>());
        reviews.get(id).add(review);

        return reviews.get(id);
    }


    public List<PerformanceReview> getPerformanceReviews(Object id) {

        return repository.findReviewsByEmployeeId(id);

    }
}

