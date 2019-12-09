package com.aman.onlinestore.reports;

import com.cognitree.onlinestore.ReportGenerator;
import com.cognitree.onlinestore.model.Purchase;

import java.util.HashMap;
import java.util.Map;

public class ProductQuantity implements ReportGenerator {

    private static final Map<String, Integer> productQuantity = new HashMap<>();

    @Override
    public void process(Purchase purchase) {
        if (productQuantity.containsKey(purchase.getItemId())) {
            int n = productQuantity.get(purchase.getItemId());
            productQuantity.put(purchase.getItemId(), n + purchase.getQuantity());
        } else {
            productQuantity.put(purchase.getItemId(), purchase.getQuantity());
        }
    }

    @Override
    public void generateReport() {
        System.out.println("Quantity for each product:" + productQuantity);
        productQuantity.clear();
    }
}
