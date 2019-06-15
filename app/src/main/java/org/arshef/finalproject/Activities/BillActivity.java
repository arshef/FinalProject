package org.arshef.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.arshef.finalproject.Models.BankAccount;
import org.arshef.finalproject.Models.Bill;
import org.arshef.finalproject.R;

import java.util.List;

public class BillActivity extends AppCompatActivity {

    public static boolean was_successful = false;

    EditText Serial, Accnum, Accpass;
    Button confirmBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Serial = findViewById(R.id.bill_id);
        Accnum = findViewById(R.id.billaccnum);
        Accpass = findViewById(R.id.billaccpass);
        confirmBtn = findViewById(R.id.confirmbill);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Bill> billList = Bill.listAll(Bill.class);
                for (Bill bill : billList) {
                    if(bill.getBill_id() == Integer.parseInt(Serial.getText().toString())) {
                        List<BankAccount> bankAccountList = BankAccount.listAll(BankAccount.class);
                        BankAccount destinationAcc = null;
                        for (BankAccount account : bankAccountList) {
                    }
                }
            }
        });
    }
}
