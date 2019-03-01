package io.github.djunicode.canteenapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.math.BigInteger;
import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {

    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        password = findViewById(R.id.passwordtxt);


    }

    public void SHAHashing()
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
    }

    public static byte[] encryptSHA(byte[] data, String shaN) throws Exception
    {
        MessageDigest sha = MessageDigest.getInstance(shaN);
        sha.update(data);
        return  sha.digest();
    }
}
