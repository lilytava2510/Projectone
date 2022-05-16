package com.revature.models;

public class RegisterObject {

    public String username;
    public String password;
    public String first;
    public String last;
    public String email;

    public int id;

    public boolean trust;



    @Override
    public String toString() {
        return "RegisterObject{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}