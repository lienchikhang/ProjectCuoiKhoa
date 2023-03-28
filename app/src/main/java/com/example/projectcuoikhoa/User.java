package com.example.projectcuoikhoa;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private int gender;
    private String email;
    private String avatar;

    public User() {
    }

    public User(String username, String password, int gender, String email, String avatar) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender(int gender) {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
