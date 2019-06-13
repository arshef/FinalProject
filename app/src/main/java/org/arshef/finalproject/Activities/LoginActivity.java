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

import org.arshef.finalproject.Models.User;
import org.arshef.finalproject.R;
import org.arshef.finalproject.Tools.StaticTools;

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
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = username.getText().toString();
                String Password = password.getText().toString();
                if (StaticTools.Authenticate(Username)) {
                    if (Authorize(Username, Password)) {
                        isLoggedIn = true;
                        user = Username;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        StaticTools.ToastMaker(LoginActivity.this, "Password is incorrect!");
                    }
                } else {
                    StaticTools.ToastMaker(LoginActivity.this, "User is not available!");
                }
            }
        });
    }

    private boolean Authorize(String username, String password) {
        if (User.find(User.class, "Username = ? and Password = ?", username, password).size() > 0) {
            return true;
        }
        return false;
    }
}
