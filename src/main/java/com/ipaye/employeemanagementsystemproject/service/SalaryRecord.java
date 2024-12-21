package com.ipaye.employeemanagementsystemproject.service;

public class SalaryRecord {
    int id;
    int  amount;

    String date;

    public SalaryRecord(int id, int amount, String date) {
        this.id=id;
        this.amount=amount;
        this.date=date;
    }

    public int getSalary() {
        return amount;
    }
}
