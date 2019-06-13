package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class BankAccount extends SugarRecord {
    int money;
    String cardNumber;
    String password;
    public BankAccount() {
    }

    public BankAccount(int money, String cardNumber, String password) {
        this.money = money;
        this.cardNumber = cardNumber;
        this.password = password;
    }
}
