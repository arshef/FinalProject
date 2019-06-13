package org.arshef.finalproject.Models;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

public class User extends SugarRecord {
    @Unique
    @NotNull
    String Username;
    @NotNull
    String Password;

    public User(String username, String password) {
        Username = username;
        Password = password;
    }
}
