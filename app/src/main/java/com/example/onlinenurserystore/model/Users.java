package com.example.onlinenurserystore.model;

public class Users {
    private String FullName;
    private String Email;
    private String PhoneNo;
    private String Password;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Users(String fullName, String email, String phoneNo, String password) {
        FullName = fullName;
        Email = email;
        PhoneNo = phoneNo;
        Password = password;
    }
}
