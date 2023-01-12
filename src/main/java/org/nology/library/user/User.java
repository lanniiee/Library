package org.nology.library.user;

public class User {
    private String userType;
    private String name;
    private String password;

    public User() {

    }

    public User(String userType, String name, String password) {
        this.userType = userType;
        this.name = name;
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return ("Name: " + name + ", userType:" + userType + ", Password: " + password );
    }
}
