package org.nology.library.database;

import org.json.CDL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class CsvToJava {

    public static void main(String[] args) {
        InputStream inputStream = CsvToJava.class.getClassLoader().getResourceAsStream("books_data.csv");
        String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();
        try {
            Files.write(Path.of("src/main/resources/books_data.json"), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser jsonParser = new JSONParser();
        try {
            Object bookFile = jsonParser.parse(new FileReader("src/main/resources/books_data.json"));
            JSONArray bookArr = (JSONArray) bookFile;

            for (int i = 0; i < bookArr.size(); i++) {
                JSONObject bookObj = (JSONObject) bookArr.get(i);
                bookObj.put("loan", false);
                bookObj.put("numOfLoan", 0);
            }

            FileWriter file = new FileWriter("src/main/resources/books_data.json");
            file.write(bookArr.toJSONString());
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
