package org.arshef.finalproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.arshef.finalproject.Models.Chat;
import org.arshef.finalproject.Models.User;
import org.arshef.finalproject.R;

import static org.arshef.finalproject.Tools.StaticTools.ToastMaker;
import static org.arshef.finalproject.Tools.StaticTools.checkChats;
import static org.arshef.finalproject.Tools.StaticTools.findUserByUsername;

public class ChattingActivity extends AppCompatActivity {
    String des_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        Button sendBtn = findViewById(R.id.sendBtn);
        final EditText msgTxt = findViewById(R.id.msgTxt);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = msgTxt.getText().toString();
                User userA = findUserByUsername(LoginActivity.user);
                User userB = findUserByUsername(des_username);
                if (userB == null) {
                    ToastMaker(ChattingActivity.this, "username is not available!");
                    return;
                }
                if (checkChats(userA, userB))
                    addChats(userA, userB, message);
                else
                    newChat(userA, userB, message);
            }
        });
    }

    private void newChat(User userA, User userB, String message) {
        Chat chat = new Chat(userA, userB);
        chat.addMessage(LoginActivity.user, message);
    }

    private void addChats(User userA, User userB, String message) {

    }


}
