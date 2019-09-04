package io.github.djunicode.canteenapp.ResponseObjects;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    @SerializedName("auth_token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
