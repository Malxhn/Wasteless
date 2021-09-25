package com.example.wasteless;

public class UserModel {
    public String name, address, phoneno, email;

    public UserModel(String name, String address, String phoneno) {

    }

    public UserModel(String name, String address, String phoneno, String email) {
        this.name = name;
        this.address = address;
        this.phoneno = phoneno;
        this.email = email;
    }
}