package com.aman.onlinestore.model;

import java.util.Date;

public class Click {
    private String sessionId;
    private Date timeStamp;
    private String itemId;
    private String category;

    /*public static Click newInstance(String sessionId, Date timeStamp, String itemId, String category) {
        return new Click(sessionId, timeStamp, itemId, category);
    }*/

    public Click(String sessionId, Date timeStamp, String itemId, String category) {
        this.sessionId = sessionId;
        this.timeStamp = timeStamp;
        this.itemId = itemId;
        this.category = category;

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

    public String getCategory() {
        return category;
    }
}
