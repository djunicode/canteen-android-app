package io.github.djunicode.canteenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;

public class LoginActivity extends BaseActivity {

    EditText password, email;
    String emailString, passwordString;
    private String token;
    public static final String TOKEN_SHARED_PREFS_KEY = "tokenKey";
    public static final String TOKEN_STRING = "token";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        password = findViewById(R.id.passwordtxt);
        email = findViewById(R.id.emailtxt);

        token = retrieveToken();

        if(token != null){
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        } else {

            emailString = email.getText().toString();
            passwordString = SHAHashing();

            token = login(emailString, passwordString);

            if(token.equals("unsuccessful")) {
                Toast.makeText(LoginActivity.this, "Email id or password incorrect", Toast.LENGTH_SHORT).show();
            } else {
                saveToken(token);
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        }
    }

    public String SHAHashing()
    {

        password = findViewById(R.id.passwordtxt);
        byte[] inputData = password.getText().toString().getBytes();
        byte[] outputData = new byte[0];

        try {
            outputData = encryptSHA(inputData,"SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
        }


        BigInteger shaData = new BigInteger(1,outputData);
        String hashedPassword = shaData.toString(16);

        return hashedPassword;
    }

    public static byte[] encryptSHA(byte[] data, String shaN) throws Exception
    {
        MessageDigest sha = MessageDigest.getInstance(shaN);
        sha.update(data);
        return  sha.digest();
    }

    public String login(String email, String password) {
        //perform post request for login operation
        return "unsuccessful"; //will return token if successful
    }
}
