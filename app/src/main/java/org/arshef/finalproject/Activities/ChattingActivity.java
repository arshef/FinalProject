package org.arshef.finalproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.arshef.finalproject.Models.Chat;
import org.arshef.finalproject.Models.ChattingDataAdapter;
import org.arshef.finalproject.Models.User;
import org.arshef.finalproject.R;

import static org.arshef.finalproject.Tools.StaticTools.ToastMaker;
import static org.arshef.finalproject.Tools.StaticTools.checkChats;
import static org.arshef.finalproject.Tools.StaticTools.findUserByUsername;

public class ChattingActivity extends AppCompatActivity {
    String des_username;
    User userA;
    User userB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        des_username = getIntent().getStringExtra("usr");
        Button sendBtn = findViewById(R.id.sendBtn);
        final EditText msgTxt = findViewById(R.id.msgTxt);
        ListView listView = findViewById(R.id.chatting_listview);
        userA = findUserByUsername(LoginActivity.user);
        userB = findUserByUsername(des_username);
        if (userB == null) {
            ToastMaker(ChattingActivity.this, "username is not available!");
            return;
        }
        final Chat self = checkChats(userA, userB);
        if (self != null) {
            ChattingDataAdapter dataAdapter = new ChattingDataAdapter(this, R.layout.chatting, self.getStrings());
            listView.setAdapter(dataAdapter);
        }
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = msgTxt.getText().toString();
                addChats(userA, userB, message);
            }
        });
    }



    private void addChats(User userA, User userB, String message) {
        Chat chat = checkChats(userA, userB);
        chat.initChat();
        chat.addMessage(userA.getUsername(), message);
        chat.update();
    }
}
