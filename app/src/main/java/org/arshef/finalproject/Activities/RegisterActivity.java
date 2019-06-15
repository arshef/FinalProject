package org.arshef.finalproject.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.arshef.finalproject.R;
import org.arshef.finalproject.Tools.StaticTools;
import org.arshef.finalproject.Models.User;

public class RegisterActivity extends AppCompatActivity {

    private String Password;
    private String Username;
    private boolean isAllowed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        final TextView username = findViewById(R.id.username);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {//
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final TextView usr_error = findViewById(R.id.usrerror);
                Username = username.getText().toString();
                if (Username.equals("")) {
                    isAllowed = false;
                    usr_error.setTextColor(Color.rgb(204, 0, 0));
                    usr_error.setText("Invalid Username  \uD83D\uDE25");
                    usr_error.setVisibility(View.VISIBLE);
                } else if (StaticTools.Authenticate(Username)) {
                    isAllowed = false;
                    usr_error.setTextColor(Color.rgb(204, 0, 0));
                    usr_error.setText("Username Invalid \uD83D\uDE25");
                    usr_error.setVisibility(View.VISIBLE);
                } else {
                    isAllowed = true;
                    usr_error.setTextColor(Color.rgb(0, 153, 51));
                    usr_error.setText("Fine \uD83D\uDE0A");
                    usr_error.setVisibility(View.VISIBLE);
                }
            }
        });
        final TextView password = findViewById(R.id.password);
        final TextView compass = findViewById(R.id.confirmpass);
        Button registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAllowed) {
                    StaticTools.ToastMaker(RegisterActivity.this, "Check username!");
                    return;
                }
                AddUser(compass.getText().toString());
                LoginActivity.isLoggedIn = true;
                StaticTools.ToastMaker(RegisterActivity.this, "Welcome!");
                isAllowed = false;
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final TextView errorLabel = findViewById(R.id.errorLabel);
        compass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Password = password.getText().toString();
                errorLabel.setVisibility(View.VISIBLE);
                String c = compass.getText().toString();
                if (c.equals("")) {
                    errorLabel.setVisibility(View.INVISIBLE);
                }
                if (c.equals(Password)) {
                    errorLabel.setTextColor(Color.rgb(0, 153, 51));
                    errorLabel.setText("Match \uD83D\uDE0A");
                } else {
                    errorLabel.setTextColor(Color.rgb(204, 0, 0));
                    errorLabel.setText("Doesn't match \uD83D\uDE25");
                }
            }
        });
    }

    private void AddUser(String s) {
        if (s.equals(Password)) {
            User user = new User(Username, Password);
            User.save(user);
        }
        StaticTools.ToastMaker(this, "Check password!");
    }
}

