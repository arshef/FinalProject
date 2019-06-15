package org.arshef.finalproject.Models;

import com.orm.SugarRecord;

public class Bill extends SugarRecord {
    private int bill_id;

    public Bill() {
    }

    public Bill(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }
}
