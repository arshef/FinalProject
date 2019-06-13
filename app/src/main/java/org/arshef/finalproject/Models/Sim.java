package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class Sim extends SugarRecord {
    String number;
    int money;

    public Sim() {
    }

    public Sim(String number) {
        this.number = number;
        money = 0;
    }
}
