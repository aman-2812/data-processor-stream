package com.aman.onlinestore;

import com.cognitree.onlinestore.reports.*;

import java.util.ArrayList;
import java.util.List;

class Registry {

    private final List<ReportGenerator> reportsPurchase = new ArrayList<>();
    private final List<ReportGenerator> reportsClick = new ArrayList<>();

    List<ReportGenerator> getRegisteredReportGeneratorsPurchase() {
        reportsPurchase.add(new ProductQuantity());
        reportsPurchase.add(new TotalQuantity());
        reportsPurchase.add(new TopQuantities());
        reportsPurchase.add(new ItemTimestamp());
        return reportsPurchase;
    }

    List<ReportGenerator> getRegisteredReportGeneratorClick() {
        reportsClick.add(new AveragePerItem());
        return reportsClick;
    }
}
