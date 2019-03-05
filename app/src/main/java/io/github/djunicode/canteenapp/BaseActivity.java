package io.github.djunicode.canteenapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_SHARED_PREFS_KEY;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_STRING;

public abstract class BaseActivity extends AppCompatActivity {

    String tokenReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void saveToken(String token) {

        SharedPreferences tokenPrefs = getSharedPreferences(TOKEN_SHARED_PREFS_KEY, MODE_PRIVATE);
        tokenPrefs.edit().putString(TOKEN_STRING, token).apply();
    }

    public String retrieveToken(){
        SharedPreferences tokenPrefs = getSharedPreferences(TOKEN_SHARED_PREFS_KEY, MODE_PRIVATE);
        tokenReturn = tokenPrefs.getString(TOKEN_STRING, null);
        return tokenReturn;
    }
}
