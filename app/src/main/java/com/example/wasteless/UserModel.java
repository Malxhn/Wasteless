package com.example.wasteless;

public class UserModel {
    public String name, address, phoneno, email;


    public UserModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UserModel(String name, String address, String phoneno) {

    }


    public UserModel(String name, String address, String phoneno, String email) {
        this.name = name;
        this.address = address;
        this.phoneno = phoneno;
        this.email = email;
    }



}