package org.arshef.finalproject.Tools;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.arshef.finalproject.Models.Chat;
import org.arshef.finalproject.Models.User;

import java.util.List;

import static com.orm.SugarRecord.listAll;

public class StaticTools {
    public static void ToastMaker(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static boolean Authenticate(String username) {
        if (User.find(User.class, "Username = ?", username).size() > 0) {
            return true;
        }
        return false;
    }

    public static User findUserByUsername(String username) {
        List<User> users = User.listAll(User.class);
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static Chat checkChats(User userA, User userB) {
        List<Chat> chats = Chat.listAll(Chat.class);
        for (Chat c :
                chats) {
            if (c.getAUser().equals(userA)&&c.getBUser().equals(userB))
                return c;
        }
        return null;
    }
}
