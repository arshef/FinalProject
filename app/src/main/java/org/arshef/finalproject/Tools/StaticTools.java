package org.arshef.finalproject.Tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import org.arshef.finalproject.Activities.LoginActivity;
import org.arshef.finalproject.Models.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

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
