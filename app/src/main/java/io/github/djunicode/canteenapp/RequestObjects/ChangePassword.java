package io.github.djunicode.canteenapp.RequestObjects;

import com.google.gson.annotations.SerializedName;

public class ChangePassword {
    @SerializedName("new_password")
    private String new_password;
    @SerializedName("current_password")
    private String old_password;
}
