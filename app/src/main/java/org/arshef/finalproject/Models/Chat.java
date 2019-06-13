package org.arshef.finalproject.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Chat extends SugarRecord {
    @Ignore
    List<String> strings;
    String storedStrings;
    User AUser;
    User BUser;

    public Chat() {
    }

    public Chat(List<String> strings, String storedStrings, User AUser, User BUser) {
        this.strings = strings;
        this.storedStrings = storedStrings;
        this.AUser = AUser;
        this.BUser = BUser;
    }
}
