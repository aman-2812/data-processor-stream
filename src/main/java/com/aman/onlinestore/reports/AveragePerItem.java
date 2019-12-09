package com.aman.onlinestore.reports;

import com.cognitree.onlinestore.ReportGenerator;
import com.cognitree.onlinestore.model.Click;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AveragePerItem implements ReportGenerator {

    private static final Logger logger= LoggerFactory.getLogger(AveragePerItem.class);

    private final Map<String, Map<String, Date>> itemSessionTimestamp = ItemTimestamp.itemSessionTimestamp;
    private final Map<String, Integer> itemClicks = new HashMap<>();
    private final Map<String, Integer> purchaseFrequency = ItemTimestamp.purchaseFrequency;
    private final Map<String, Double> avgClicks = new HashMap<>();

    @Override
    public void process(Click click) {
        Map<String, Date> sessionTime;
        sessionTime = itemSessionTimestamp.get(click.getItemId());
        if (sessionTime != null) {
            Date date = sessionTime.get(click.getSessionId());
            if (date != null && click.getTimeStamp().before(date)) {
                if (itemClicks.containsKey(click.getItemId())) {
                    int n = itemClicks.get(click.getItemId());
                    itemClicks.put(click.getItemId(), ++n);
                } else {
                    itemClicks.put(click.getItemId(), 1);
                }
            }
        }
    }

    @Override
    public void generateReport() {
        logger.info("generateReport() : Generating report.");
        for (Map.Entry<String, Integer> entry : purchaseFrequency.entrySet()) {
            if (itemClicks.containsKey(entry.getKey())) {
                double totalClicks = itemClicks.get(entry.getKey());
                if (totalClicks == 0) {
                    avgClicks.put(entry.getKey(), 0.0);
                } else {
                    double average = totalClicks / entry.getValue();
                    avgClicks.put(entry.getKey(), average);
                }
            } else {
                avgClicks.put(entry.getKey(), 0.0);
            }
        }
        System.out.println("Average Click per Item: "+avgClicks);
    }
}