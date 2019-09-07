package io.github.djunicode.canteenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;

import io.github.djunicode.canteenapp.RequestObjects.SignInRequest;
import io.github.djunicode.canteenapp.ResponseObjects.SignInResponse;
import io.github.djunicode.canteenapp.RetrofitInterfaces.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    EditText password, sap;
    Button loginButton;
    String sapString, passwordString;
    private String token;
    private ApiInterface apiInterface;
    public static final String TOKEN_SHARED_PREFS_KEY = "tokenKey";
    public static final String TOKEN_STRING = "token";
    Button login;
    TextInputLayout inputLayoutEmail,inputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        inputLayoutEmail = (TextInputLayout)findViewById(R.id.login_layout_email);
        inputLayoutPassword =(TextInputLayout)findViewById(R.id.login_layout_pass);

        password = findViewById(R.id.passwordtxt);
        sap = findViewById(R.id.saptxt);
        loginButton = findViewById(R.id.loginbtn);

        token = retrieveToken();
        apiInterface = customRetrofit.create(ApiInterface.class);

//        email.addTextChangedListener(new MyTextWatcher(email));
//        password.addTextChangedListener(new MyTextWatcher(password));



//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!validateEmail()) {
//                    return;
//                }
//
//                if (!validatePassword()) {
//                    return;
//                }
//                if (email.getText().toString().equals("admin@email.com")  && password.getText().toString().equals("admin")){
//
//                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
//                }
//            }
//        });

        if(token != null){
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        } else {

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sapString = sap.getText().toString();
//                    passwordString = SHAHashing();
                    passwordString = password.getText().toString();

                    login(sapString, passwordString);
                }
            });

        }
    }
    public void goToSignUp(View view){
        Intent intent=new Intent(LoginActivity.this,SignUpStudent.class);
        startActivity(intent);
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

    public void login(String sap, String password) {

        SignInRequest post = new SignInRequest(sap, password);

        Call<SignInResponse> call = apiInterface.createPostLogin(post);
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



    private boolean validateEmail() {
        String emailTxt = sap.getText().toString().trim();

        if (emailTxt.isEmpty() || !isValidEmail(emailTxt)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(sap);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.saptxt:
                    validateEmail();
                    break;
                case R.id.passwordtxt:
                    validatePassword();
                    break;
            }
        }
    }

}
