package com.ipaye.employeemanagementsystemproject.Model;

public class Report {

    String monthlySalesReport;

    String ReportSalesReview;

    public Report(String monthlySalesReport, String reportSalesReview){
        this.monthlySalesReport = monthlySalesReport;
        this.ReportSalesReview = reportSalesReview;

    }

    @Override
    public boolean equals(Object object){

        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Report report = (Report) object;
        return monthlySalesReport.equals(report.monthlySalesReport) && ReportSalesReview.equals(report.ReportSalesReview);
    }
}
