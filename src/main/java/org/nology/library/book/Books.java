package org.nology.library.book;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.nology.library.database.ReadJson;

import java.util.ArrayList;
import java.util.List;

public class Books {

    private static List<Book> allBooks = new ArrayList<>();

    public List<Book> getBooks () {
        ReadJson booksJSon = new ReadJson();

        JSONArray books = (JSONArray) booksJSon.readJson();

        for(int i = 0; i < books.size(); i++) {
            JSONObject bookObj = (JSONObject) books.get(i);
            String title = (String) bookObj.get("Title");
            String author = (String) bookObj.get("Author");
            String id = (String) bookObj.get("Number");
            allBooks.add(new Book(title, author, id));
        }
        return allBooks;
    }

}
