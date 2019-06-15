package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class Rating extends SugarRecord {
    News news;
    int rate;
    User user;

    public Rating() {
    }

    public Rating(User user, News news, int rate) {
        this.news = news;
        this.rate = rate;
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public int getRate() {
        return rate;
    }

    public User getUser() {
        return user;
    }
}
