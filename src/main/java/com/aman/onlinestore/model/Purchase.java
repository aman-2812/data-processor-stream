package com.aman.onlinestore.model;

import java.util.Date;

public class Purchase {

    private String sessionId;
    private Date timeStamp;
    private String itemId;
    private int price;
    private int quantity;

/*
    public static Purchase newInstance(String sessionId, Date timeStamp, String itemId, int price, int quantity) {
        return new Purchase(sessionId, timeStamp, itemId, price, quantity);
    }
*/

    public Purchase(String sessionId, Date timeStamp, String itemId, int price, int quantity) {
        this.sessionId = sessionId;
        this.timeStamp = timeStamp;
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getItemId() {
        return itemId;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
