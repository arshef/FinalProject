package org.arshef.finalproject.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.arshef.finalproject.R;

public class LoginActivity extends AppCompatActivity {

    public static boolean isLoggedIn = false;
    public static String user;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView signupLabel = findViewById(R.id.signupLabel);
        signupLabel.setPaintFlags(signupLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signupLabel.setLinksClickable(true);
        signupLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        final EditText username = findViewById(R.id.username);
        if (username.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        final EditText password = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.login_btn);
    }

}
