package com.aman.onlinestore.reports;

import com.cognitree.onlinestore.ReportGenerator;
import com.cognitree.onlinestore.model.Purchase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ItemTimestamp implements ReportGenerator {

    static final Map<String, Map<String, Date>> itemSessionTimestamp = new HashMap<>();
    static final Map<String, Integer> purchaseFrequency = new HashMap<>();

    @Override
    public void process(Purchase purchase) {

        if (purchase.getQuantity() != 0) {
            if (itemSessionTimestamp.containsKey(purchase.getItemId())) {
                Map<String, Date> sessionTimeStamp = itemSessionTimestamp.get(purchase.getItemId());
                if (sessionTimeStamp.containsKey(purchase.getSessionId())) {
                    Date sessionDate = sessionTimeStamp.get(purchase.getSessionId());
                    if (purchase.getTimeStamp() != null && sessionDate.after(purchase.getTimeStamp())) {
                        sessionTimeStamp.put(purchase.getSessionId(), purchase.getTimeStamp());
                    }
                } else {
                    sessionTimeStamp.put(purchase.getSessionId(), purchase.getTimeStamp());
                    if (purchaseFrequency.containsKey(purchase.getItemId())){
                        int n=purchaseFrequency.get(purchase.getItemId());
                        purchaseFrequency.put(purchase.getItemId(),++n);
                    } else{
                     purchaseFrequency.put(purchase.getItemId(),1);
                    }
                }
            } else {
                Map<String, Date> sessionTimeStamp = new HashMap<>();
                sessionTimeStamp.put(purchase.getSessionId(), purchase.getTimeStamp());
                itemSessionTimestamp.put(purchase.getItemId(), sessionTimeStamp);
                purchaseFrequency.put(purchase.getItemId(),1);
            }
        }
    }
}
