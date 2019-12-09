package com.aman.onlinestore.reports;

import com.cognitree.onlinestore.ReportGenerator;
import com.cognitree.onlinestore.model.Purchase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TopQuantities implements ReportGenerator {

    private final Map<Integer, Integer> timeQuantity = new HashMap<>();

    @Override
    public void process(Purchase purchase) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(purchase.getTimeStamp());
       if (timeQuantity.containsKey(calendar.get(Calendar.HOUR_OF_DAY))) {
            int n = timeQuantity.get(calendar.get(Calendar.HOUR_OF_DAY));
            timeQuantity.put((calendar.get(Calendar.HOUR_OF_DAY)), n + purchase.getQuantity());
        } else {
            timeQuantity.put((calendar.get(Calendar.HOUR_OF_DAY)), purchase.getQuantity());
        }
    }

    @Override
    public void generateReport() {
        timeQuantity.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(a -> System.out.println("Time: "
                        + a.getKey() + ":00 to " + (a.getKey() + 1) + ":00 . " +
                        "Quantity= " + a.getValue()));
        timeQuantity.clear();
    }
}
