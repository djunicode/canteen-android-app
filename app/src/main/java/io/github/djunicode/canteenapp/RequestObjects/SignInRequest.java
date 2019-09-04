package io.github.djunicode.canteenapp.RequestObjects;

import com.google.gson.annotations.SerializedName;

public class SignInRequest {
    @SerializedName("username")
    private String sap;
    @SerializedName("password")
    private String password;

    public SignInRequest(String sap, String password) {
        this.sap = sap;
        this.password = password;
    }
}
