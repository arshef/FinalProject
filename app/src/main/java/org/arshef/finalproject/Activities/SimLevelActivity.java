package org.arshef.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.arshef.finalproject.R;

public class SimLevelActivity extends AppCompatActivity {
    TextView mytxt;
    Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simres);
        mytxt = findViewById(R.id.sim_money);
        mytxt.setText(SimcardActivity.nahayi);
        Back = findViewById(R.id.sim_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimLevelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
