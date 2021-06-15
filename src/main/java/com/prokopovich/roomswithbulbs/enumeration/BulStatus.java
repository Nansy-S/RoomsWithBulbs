package com.prokopovich.roomswithbulbs.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum BulStatus {

    ON("ON"),
    OFF("OFF");

    private final String title;

    BulStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static BulStatus fromString(String text) {
        for (BulStatus b : BulStatus.values()) {
            if (b.title.equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<String> getAllTitle() {
        List<String> roleTitleList = new ArrayList<>();
        for(BulStatus value : BulStatus.values()) {
            roleTitleList.add(value.getTitle());
        }
        return roleTitleList;
    }
}
