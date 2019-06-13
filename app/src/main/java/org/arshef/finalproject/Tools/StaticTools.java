package org.arshef.finalproject.Tools;

import android.content.Context;
import android.widget.Toast;

import org.arshef.finalproject.Models.User;

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
}
