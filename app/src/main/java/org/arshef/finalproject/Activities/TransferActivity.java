package org.arshef.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.arshef.finalproject.Models.BankAccount;
import org.arshef.finalproject.R;
import org.arshef.finalproject.Tools.StaticTools;

import java.util.List;

public class TransferActivity extends AppCompatActivity {
    EditText originNo, accPass, destinationNo, amount;
    Button confirmBtn;
    public static boolean was_successful = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        originNo = findViewById(R.id.origin_cardnum);
        accPass = findViewById(R.id.accountpass);
        destinationNo = findViewById(R.id.destinationacc);
        amount = findViewById(R.id.transferamount);
        confirmBtn = findViewById(R.id.confirmtransfer);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BankAccount> bankAccountList = BankAccount.listAll(BankAccount.class);
                BankAccount destinationAcc = null;
                for (BankAccount account : bankAccountList) {
                    if (account.getCardNumber().equals(destinationNo.getText().toString())) {
                        destinationAcc = BankAccount.findById(BankAccount.class, account.getId());
                    }
                }

                if (destinationAcc == null) {
                    StaticTools.ToastMaker(TransferActivity.this, "Destination bank account not found!");
                }
                boolean b = false;
                for (BankAccount account : bankAccountList) {
                    if (account.getCardNumber().equals(originNo.getText().toString())) {
                        if (account.getCardNumber().equals(destinationAcc.getCardNumber())) {
                            StaticTools.ToastMaker(TransferActivity.this, "Invalid account number!");
                        }
                        else if (account.getPassword().equals(accPass.getText().toString())) {
                            if (account.getMoney() >= Long.parseLong(amount.getText().toString())) {
                                account.setMoney(account.getMoney() - Integer.parseInt(amount.getText().toString()));
                                destinationAcc.setMoney(destinationAcc.getMoney() + Integer.parseInt(amount.getText().toString()));
                                account.save();
                                destinationAcc.save();
                                Intent myintent = new Intent(TransferActivity.this, MainActivity.class);
                                was_successful = true;
                                startActivity(myintent);
                            }
                            else {
                                StaticTools.ToastMaker(TransferActivity.this, "Insufficient funds!");
                            }
                        }
                        else {
                            StaticTools.ToastMaker(TransferActivity.this, "Password is incorrect!");
                        }
                        b = true;
                    }
                }
                if (!b)
                    StaticTools.ToastMaker(TransferActivity.this, "Origin bank account not found!");
            }
        });
    }
}
