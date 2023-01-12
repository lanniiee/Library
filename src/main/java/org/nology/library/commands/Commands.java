package org.nology.library.commands;

import org.nology.library.book.Book;
import org.nology.library.database.WriteJson;
import org.nology.library.user.User;


import java.util.List;
import java.util.Scanner;

public class Commands {

    private String input;
    private String password;
    private String name;
    private List<User> users;
    private List<Book> books;
    private boolean open;
    private WriteJson writeJson;

    public Commands (List<User> users, List<Book> books, boolean open, WriteJson writeJson) {
        this.users = users;
        this.books = books;
        this.open = open;
        this.writeJson = writeJson;
    }

    public String handleCommands () {
            handleAuthentication();
        return input;
    }

    public String handleAuthentication() {
        printMessage("\nWelcome to Chalton Library. Enter as:\n");
        printMessage("1. Visitor");
        printMessage("2. Staff");
        printMessage("3. Exit");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        String userInputClean = String.valueOf(userInput.toLowerCase().charAt(0));
        input = userInputClean;

        if (input.equals("1")) {
            selectVisitorType();
            if(input.equals("2")) {
                selectVisitorType();
            }
        } else if (input.equals("2")) {
            selectStaffChoice();
        } else if (input.equals("3")) {
            open = false;
            printMessage("Thank you for visiting. Goodbye!");
            return input;
        } else {
            printMessage("Enter a valid option. If you're a visitor, input 1 and press enter.");
        }

        return input;
    }

    public int selectStaffChoice() {
        printMessage("\nPlease login to access your account \n");
        printMessage("1. Login");
        printMessage("2. Exit");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        String userInputClean = String.valueOf(userInput.toLowerCase().charAt(0));
        input = userInputClean;
        return Integer.parseInt(input);
    }

    public int selectVisitorType() {
        printMessage("\n1. Register");
        printMessage("2. Login");
        printMessage("3. Back");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        String userInputClean = String.valueOf(userInput.toLowerCase().charAt(0));
        input = userInputClean;

        if (input.equals("1")) {
            String user = "visitor";
            System.out.println("Enter your name");
            String name = scanner.next();
            System.out.println("Enter your password");
            String password = scanner.next();
            writeJson.addUser(user, name, password);
            System.out.println("Account successfully created. Login in");
        } else if (input.equals("2")) {
            handleLogin();
        }
        return Integer.parseInt(input);

    }

    public void handleLogin() {
        handleName();

    }

    public void handleName() {
        printMessage("\nName:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        name = userInput;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getName().equals(name)) {
                handlePassword();
                if (users.get(i).getPassword().equals(password)) {
                    printMessage("\nLogin Successful");
                    handleOptions();
                } else {
                    handlePassword();
                }
            }
        }
    }

    public void handlePassword() {
        printMessage("\nPassword:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        password = userInput;
    }

    protected void printMessage(String message) {
        System.out.println(message);
    }

    public int handleOptions() {
        printMessage("\n1. See all books");
        printMessage("2. Loan a book");
        printMessage("3. Exit");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        String userInputClean = String.valueOf(userInput.toLowerCase().charAt(0));
        input = userInputClean;
        if (input.equals("1")) {
            for (int i = 0; i < books.size(); i++) {
                System.out.println(i + ". '" + books.get(i).getTitle() + "' by " + books.get(i).getAuthor());
            }
        } else if (input.equals("2")) {
            loanABook();
        } else if (input.equals("3")) {
            return (-1);
        }

        handleOptions();
        return 0;
    }

    public void loanABook() {

    }

}
