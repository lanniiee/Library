package org.nology.library.database;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.nology.library.user.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadUserJson {

    private static List<User> allUsers;

    public List<User> readUser() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/users.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray users = (JSONArray) obj;
            allUsers = users;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

}
