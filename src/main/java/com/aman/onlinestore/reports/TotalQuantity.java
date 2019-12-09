package com.aman.onlinestore.reports;

import com.cognitree.onlinestore.ReportGenerator;
import com.cognitree.onlinestore.model.Purchase;

public class TotalQuantity implements ReportGenerator {

    private int total = 0;

    @Override
    public void process(Purchase purchase) {
        total = total + purchase.getQuantity();
    }

    @Override
    public void generateReport() {
        System.out.println("Total Quantity Sold: " + total);
        total=0;
    }

}
