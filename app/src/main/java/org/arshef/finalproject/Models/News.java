package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class News extends SugarRecord {
    String title;
    String text;

    public News() {
    }

    public News(String title, String text) {
        this.title = title;
        this.text = text;
    }
}