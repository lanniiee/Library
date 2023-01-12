package org.nology.library.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.nology.library.book.Book;

public class ReadJson {

    private static List<Book> allBooks;

    public List<Book> readJson() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/books_data.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray books = (JSONArray) obj;
            allBooks = books;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return allBooks;

    }

}
