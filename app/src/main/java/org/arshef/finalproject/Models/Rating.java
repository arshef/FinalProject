package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class Rating extends SugarRecord {
    News news;
    int rate;
    User user;

    public Rating() {
    }
}
