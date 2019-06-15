package org.arshef.finalproject.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import org.arshef.finalproject.Tools.StaticTools;

import java.util.ArrayList;
import java.util.List;

public class Chat extends SugarRecord {
    @Ignore
    List<SingleChat> strings;
    String storedStrings;
    User AUser;
    User BUser;

    public Chat() {
        strings = new ArrayList<>();
    }

    public Chat(User AUser, User BUser) {
        this.AUser = AUser;
        this.BUser = BUser;
        strings = new ArrayList<>();
    }

    public List<SingleChat> getStrings() {
        return strings;
    }

    public User getAUser() {
        return AUser;
    }

    public User getBUser() {
        return BUser;
    }

    public void addMessage(String username, String s) {
        strings.add(new SingleChat(StaticTools.findUserByUsername(username), s));
        store();
    }

    private void store() {
        String temp = "";
        for (SingleChat s :
                strings) {
            String string = String.format("%s**%s", s.user.Username, s.message);
            temp = string + temp + "/";
        }
        storedStrings = temp;
        Chat.save(this);
    }
}
