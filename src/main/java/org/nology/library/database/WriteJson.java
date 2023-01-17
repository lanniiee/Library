package org.nology.library.database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class WriteJson {

    public void writeJson() {
        JSONObject userDetails = new JSONObject();
        userDetails.put("user", "visitor");
        userDetails.put("name", "visitor1");
        userDetails.put("password", "password1");

        JSONArray userList = new JSONArray();
        userList.add(userDetails);

        try (FileWriter file = new FileWriter("src/main/resources/users.json")) {
            file.write(userList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addUser(String user, String name, String password) {
        JSONObject obj = new JSONObject();
        obj.put("user", user);
        obj.put("name", name);
        obj.put("password", password);

        JSONParser jsonParser = new JSONParser();

        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/users.json"));
            JSONArray userArr = (JSONArray) userFile;
            userArr.add(obj);
            FileWriter file = new FileWriter("src/main/resources/users.json");
            file.write((userArr.toJSONString()));
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



}
