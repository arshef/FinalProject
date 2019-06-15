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
import org.arshef.finalproject.Models.Sim;
import org.arshef.finalproject.R;
import org.arshef.finalproject.Tools.StaticTools;

import java.util.List;

public class SimcardActivity extends AppCompatActivity {

    EditText pNumber, Accnum, Accpass, Amount;
    Button confirmBtn;
    Boolean b = false;
    public static int nahayi = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simcard);
        pNumber = findViewById(R.id.simnum);
        Accnum = findViewById(R.id.simaccnum);
        Accpass = findViewById(R.id.simaccpass);
        Amount = findViewById(R.id.sharjamount);
        confirmBtn = findViewById(R.id.confirmsim);

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
                    StaticTools.ToastMaker(SimcardActivity.this, "Unavailable bank account!");
                } else {
                    List<Sim> simList = Sim.listAll(Sim.class);
                    for (Sim sim : simList) {
                        if (sim.getNumber() == pNumber.getText().toString()) {
                            if (myaccount.getMoney() < Integer.parseInt(Amount.getText().toString())) {
                                b = true;
                                StaticTools.ToastMaker(SimcardActivity.this, "Insufficient funds!");
                            }
                            else{
                                myaccount.setMoney(myaccount.getMoney() - Integer.parseInt(Amount.getText().toString()));
                                sim.setMoney(Integer.parseInt(Amount.getText().toString()) + sim.getMoney());
                                myaccount.save();
                                sim.save();
                                Intent intent = new Intent(SimcardActivity.this, SimLevelActivity.class);
                                b = true;
                                nahayi = sim.getMoney();
                                startActivity(intent);
                            }
                        }
                    }
                    if(!b)
                        StaticTools.ToastMaker(SimcardActivity.this, "Phone not found!");
                }
            }
        });
    }
}
