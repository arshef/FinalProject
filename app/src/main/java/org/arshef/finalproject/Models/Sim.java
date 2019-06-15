package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class Sim extends SugarRecord {
    String number;
    int money;

    public Sim() {
    }

    public Sim(String number, int money) {
        this.number = number;
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
