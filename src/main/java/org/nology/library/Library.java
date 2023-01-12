package org.nology.library;
import org.nology.library.book.Books;
import org.nology.library.commands.Commands;


import org.nology.library.database.WriteJson;
import org.nology.library.user.Users;


public class Library {

    private static boolean open = true;

    public static void main(String[] args) {
        Books books = new Books();
        Users users = new Users();
        WriteJson writeJson = new WriteJson();

        Commands commands = new Commands(users.getUsers(), books.getBooks(), open, writeJson);
        commands.handleCommands();




    }
}