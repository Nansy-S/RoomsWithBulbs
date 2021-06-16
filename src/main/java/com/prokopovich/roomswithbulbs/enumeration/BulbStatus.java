package com.prokopovich.roomswithbulbs.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum BulbStatus {

    ON("ON"),
    OFF("OFF");

    private final String title;

    BulbStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static BulbStatus fromString(String text) {
        for (BulbStatus b : BulbStatus.values()) {
            if (b.title.equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<String> getAllTitle() {
        List<String> roleTitleList = new ArrayList<>();
        for(BulbStatus value : BulbStatus.values()) {
            roleTitleList.add(value.getTitle());
        }
        return roleTitleList;
    }
}
