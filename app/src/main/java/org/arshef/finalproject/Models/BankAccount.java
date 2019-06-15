package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class BankAccount extends SugarRecord {
    private int money;
    private String cardNumber;
    private String password;

    public BankAccount() {
    }

    public BankAccount(int money, String cardNumber, String password) {
        this.money = money;
        this.cardNumber = cardNumber;
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
