package com.example.onlinenurserystore.model;

public class Users {
    private String Name;
    private String UserName;
    private String Email;
    private String Password;
    private String PhoneNumber;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Users(String name, String userName, String email, String password) {
        Name = name;
        UserName = userName;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
    }
}
