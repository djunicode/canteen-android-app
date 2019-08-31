package io.github.djunicode.canteenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;

import io.github.djunicode.canteenapp.RequestObjects.SignInRequest;
import io.github.djunicode.canteenapp.ResponseObjects.SignInResponse;
import io.github.djunicode.canteenapp.RetrofitInterfaces.LoginInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    EditText password, email;
    Button loginButton;
    String emailString, passwordString;
    private String token;
    private LoginInterface loginInterface;
    public static final String TOKEN_SHARED_PREFS_KEY = "tokenKey";
    public static final String TOKEN_STRING = "token";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        password = findViewById(R.id.passwordtxt);
        email = findViewById(R.id.emailtxt);
        loginButton = findViewById(R.id.loginbtn);

        token = retrieveToken();

        if(token != null){
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        } else {

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    emailString = email.getText().toString();
                    passwordString = SHAHashing();

                    login(emailString, passwordString);
                }
            });

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

    public void login(String email, String password) {

        SignInRequest post = new SignInRequest(email, password);

        Call<SignInResponse> call = loginInterface.createPostLogin(post);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "User name or password incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }
                SignInResponse signInResponse = response.body();
                token = signInResponse.getToken();

                saveToken(token);
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Sign In failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
