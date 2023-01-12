package org.nology.library.book;

public class Book {

    private String Title;
    private String author;
    private String id;
    private boolean loan;
    private int numOfLoan;

    public Book(String title, String author, String id) {
        this.Title = title;
        this.author = author;
        this.id = id;
    }

    public Book() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        validateBookTitle(title);
        this.Title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLoan() {
        return loan;
    }

    public void setLoan(boolean loan) {
        this.loan = loan;
    }

    public int getNumOfLoan() {
        return numOfLoan;
    }

    public void setNumOfLoan(int numOfLoan) {
        this.numOfLoan = numOfLoan;
    }

    private void validateBookTitle(String name) {
        if (name == null || "".equals(name.trim()) ) {
            throw new IllegalArgumentException("Enter a valid Title");
        }
    }


    @Override
    public String toString() {
        return (id + "." + Title + " by " + author) ;
    }

}
