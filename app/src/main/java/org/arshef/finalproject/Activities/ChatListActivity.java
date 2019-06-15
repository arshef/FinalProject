package org.arshef.finalproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.arshef.finalproject.Models.Chat;
import org.arshef.finalproject.Models.ChattingDataAdapter;
import org.arshef.finalproject.Models.ChattingListDataAdapter;
import org.arshef.finalproject.Models.User;
import org.arshef.finalproject.R;
import org.arshef.finalproject.Tools.StaticTools;

import java.util.List;

import static org.arshef.finalproject.Tools.StaticTools.ToastMaker;
import static org.arshef.finalproject.Tools.StaticTools.checkChats;
import static org.arshef.finalproject.Tools.StaticTools.findUserByUsername;

public class ChatListActivity extends AppCompatActivity {
    String sUser = "";
    User userA;
    User userB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ListView listView = findViewById(R.id.chats_listview);
        final EditText editText = findViewById(R.id.searchUser);
        final EditText msgText = findViewById(R.id.msgTxtList);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sUser = s.toString();
            }
        });
        userA = findUserByUsername(LoginActivity.user);
        userB = findUserByUsername(sUser);
        if (userB == null) {
            ToastMaker(ChatListActivity.this, "username is not available!");
            return;
        }
        List<Chat> chats = getChatList(userA);
        ChattingListDataAdapter dataAdapter = new ChattingListDataAdapter(this, R.layout.chat_list, chats);
        listView.setAdapter(dataAdapter);
//        if (self != null) {
//            ChattingDataAdapter dataAdapter = new ChattingDataAdapter(this, R.layout.chatting, self.getStrings());
//            listView.setAdapter(dataAdapter);
//        }
        Button sendBtn = findViewById(R.id.sendBtnList);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = msgText.getText().toString();
                if (StaticTools.checkChats(userA, userB) == null) {
                    newChat(userA, userB, s);
                    Reset();
                } else
                    addChats(userA, userB, s);
            }
        });
    }

    private void Reset() {
        Intent intent = new Intent(ChatListActivity.this, ChatListActivity.class);
        startActivity(intent);
    }

    private List<Chat> getChatList(User username) {
        List<Chat> chats = Chat.listAll(Chat.class);
        for (Chat chat : chats) {
            if (chat.getBUser().equals(username))
                chats.add(chat);
        }
        return chats;
    }

    private void newChat(User userA, User userB, String message) {
        Chat chat = new Chat(userA, userB);
        chat.addMessage(LoginActivity.user, message);
        chat.save();
    }

    private void addChats(User userA, User userB, String message) {
        Chat chat = checkChats(userA, userB);
        chat.initChat();
        chat.addMessage(userA.getUsername(), message);
        chat.update();
    }
}
