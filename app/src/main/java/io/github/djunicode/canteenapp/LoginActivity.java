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
import android.widget.Button;
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
    Button login;
    TextInputLayout inputLayoutEmail,inputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        inputLayoutEmail = (TextInputLayout)findViewById(R.id.login_layout_email);
        inputLayoutPassword =(TextInputLayout)findViewById(R.id.login_layout_pass);

        password = findViewById(R.id.passwordtxt);
        email = findViewById(R.id.emailtxt);

        token = retrieveToken();

        email.addTextChangedListener(new MyTextWatcher(email));
        password.addTextChangedListener(new MyTextWatcher(password));

        login = (Button)findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail()) {
                    return;
                }

                if (!validatePassword()) {
                    return;
                }
                if (email.getText().toString().equals("admin@email.com")  && password.getText().toString().equals("admin")){

                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        });

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



    private boolean validateEmail() {
        String emailTxt = email.getText().toString().trim();

        if (emailTxt.isEmpty() || !isValidEmail(emailTxt)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(email);
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
                case R.id.emailtxt:
                    validateEmail();
                    break;
                case R.id.passwordtxt:
                    validatePassword();
                    break;
            }
        }
    }

}
