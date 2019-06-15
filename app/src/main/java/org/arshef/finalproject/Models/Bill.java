package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class Bill extends SugarRecord {
    int Code;
    int Money;

    public Bill() {
    }

    public Bill(int code, int money) {
        Code = code;
        Money = money;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

}
