package io.github.djunicode.canteenapp.RequestObjects;

public class SignInRequest {
    private String sap, password;

    public SignInRequest(String sap, String password) {
        this.sap = sap;
        this.password = password;
    }
}
