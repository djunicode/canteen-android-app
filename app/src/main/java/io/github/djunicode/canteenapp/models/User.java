package io.github.djunicode.canteenapp.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    String email;

    @SerializedName("username")
    String username;

    @SerializedName("id")
    int id;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

