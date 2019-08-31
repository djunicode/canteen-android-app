package io.github.djunicode.canteenapp.ResponseObjects;

public class SignInResponse {
    private String token, sap, name, phone;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSap() {
        return sap;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
