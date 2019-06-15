package org.arshef.finalproject.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.arshef.finalproject.R;

public class TransferActivity extends AppCompatActivity {
    EditText originNo, accPass, destinationNo, amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        originNo = findViewById(R.id.origin_cardnum);
        accPass = findViewById(R.id.accountpass);
        destinationNo = findViewById(R.id.destinationacc);
        amount = findViewById(R.id.transferamount);



    }
}
