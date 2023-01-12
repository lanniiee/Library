package org.nology.library.user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.nology.library.database.ReadUserJson;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private static List<User> allUsers = new ArrayList<>();

    public List<User> getUsers() {
        ReadUserJson readUserJson = new ReadUserJson();

        JSONArray users = (JSONArray) readUserJson.readUser();

        for (int i = 0; i < users.size(); i++) {
            JSONObject userObj = (JSONObject) users.get(i);
            String name = (String) userObj.get("name");
            String password = (String) userObj.get("password");
            String user = (String) userObj.get("user");
            allUsers.add(new User(user, name, password));
        }
        return allUsers;
    }
}
