package com.ipaye.employeemanagementsystemproject.service;

public class PerformanceReport {
    double averageCommunication;

    double AverageTeamwork;
    double AverageProblemSolving;



    public PerformanceReport(double averageCommunication, double AverageTeamwork, double AverageProblemSolving) {
        this.averageCommunication=averageCommunication;
        this.AverageTeamwork=AverageTeamwork;
        this.AverageProblemSolving=AverageProblemSolving;
    }

    public double getAverageCommunication() {
        return averageCommunication;
    }

    public double getAverageTeamwork() {
        return AverageTeamwork;
    }

    public double getAverageProblemSolving() {
        return AverageProblemSolving;
    }
}
