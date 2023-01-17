package org.nology.library.commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.nology.library.book.Book;
import org.nology.library.database.WriteJson;
import org.nology.library.user.User;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private String loanedBook;
    private long numOfLoan;
    private boolean loggedIn = false;

    public Commands (List<User> users, List<Book> books, boolean open, WriteJson writeJson) {
        this.users = users;
        this.books = books;
        this.open = open;
        this.writeJson = writeJson;
    }

    public void handleCommands () {
        while (open == true) {
            handleAuthentication();
        }
    }

    public void handleAuthentication() {
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
        } else {
            printMessage("Enter a valid option. If you're a visitor, input 1 and press enter.");
        }

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

    public void selectVisitorType() {
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
            System.out.println("Account successfully created");
            loggedIn = true;
            handleLogin();
        } else if (input.equals("2")) {
            handleLogin();
        }

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
                    loggedIn = true;
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

    public void handleOptions() {
        while (loggedIn == true) {
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
                open = false;
                loggedIn = false;
            }
        }

    }

    public void loanABook() {
        System.out.println("Enter the book number you would like to loan");
        Scanner scanner = new Scanner(System.in);
        String bookNum = scanner.nextLine();

        updateLoan(bookNum);
        updateUserLoan(name);
    }

    public void updateLoan(String bookNumber) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object bookFile = jsonParser.parse(new FileReader("src/main/resources/books_data.json"));
            JSONArray bookArr = (JSONArray) bookFile;
            for(int i = 0; i < bookArr.size(); i++) {
                JSONObject bookObj = (JSONObject) bookArr.get(i);
                if (bookObj.get("Number").equals(bookNumber)) {
                    if (!bookObj.get("loan").equals(true)) {
                        numOfLoan = (long) bookObj.get("numOfLoan");
                        numOfLoan++;
                        loanedBook = (String) bookObj.get("Title");
                        bookObj.put("numOfLoan", numOfLoan);
                        bookObj.put("loan", true);
                        bookArr.set(i, bookObj);
                        System.out.println("You have successfully loaned " + bookObj.get("Title"));
                    } else {
                        System.out.println( bookObj.get("Title") + " is unavailable for loan.");
                    }
                }
            }
            FileWriter file = new FileWriter("src/main/resources/books_data.json");
            file.write(bookArr.toJSONString());
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

    public void updateUserLoan(String name) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/users.json"));
            JSONArray userArr = (JSONArray) userFile;
            for (int i = 0; i < userArr.size(); i++) {
                JSONObject userObj = (JSONObject) userArr.get(i);
                if (userObj.get("name").equals(name)) {
                    userObj.put("loan", loanedBook);
                    userArr.set(i, userObj);
                }
            }
            FileWriter file = new FileWriter("src/main/resources/users.json");
            file.write(userArr.toJSONString());
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
