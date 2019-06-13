package org.arshef.finalproject.Models;

public class Suggestion {
    public int position;
    public double rate;
    public News news;

    public Suggestion(int position, double rate, News news) {
        this.position = position;
        this.rate = rate;
        this.news = news;
    }
}
