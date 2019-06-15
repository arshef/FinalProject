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
import org.arshef.finalproject.Tools.StaticTools;

import java.util.List;

public class BillActivity extends AppCompatActivity {

    public static boolean was_successful = false;

    EditText Serial, Accnum, Accpass;
    Button confirmBtn;
    Boolean b = false;

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
                BankAccount myaccount = null;
                List<BankAccount> bankaccList = BankAccount.listAll(BankAccount.class);
                for (BankAccount account : bankaccList) {
                    if (account.getCardNumber().equals(Accnum.getText().toString())) {
                        if (account.getPassword().equals(Accpass.getText().toString())) {
                            myaccount = BankAccount.findById(BankAccount.class, account.getId());
                        }
                    }
                }

                if (myaccount == null) {
                    StaticTools.ToastMaker(BillActivity.this, "Unavailable bank account!");
                } else {
                    List<Bill> billList = Bill.listAll(Bill.class);
                    for (Bill bill : billList) {
                        if (bill.getCode() == Integer.parseInt(Serial.getText().toString())) {
                            if(bill.getMoney() == 0){
                                StaticTools.ToastMaker(BillActivity.this, "Bill is already paid!");
                            }
                            else if (myaccount.getMoney() < bill.getMoney()) {
                                StaticTools.ToastMaker(BillActivity.this, "Insufficient funds!");
                            }
                            else{
                                myaccount.setMoney(myaccount.getMoney() - bill.getMoney());
                                bill.setMoney(0);
                                myaccount.save();
                                bill.save();
                                Intent intent = new Intent(BillActivity.this, MainActivity.class);
                                was_successful = true;
                                b = true;
                                startActivity(intent);
                            }
                        }
                    }
                    if(!b)
                        StaticTools.ToastMaker(BillActivity.this, "Bill not found!");
                }
            }
        });
    }
}
