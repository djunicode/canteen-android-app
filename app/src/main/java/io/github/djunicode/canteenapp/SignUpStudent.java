package io.github.djunicode.canteenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import io.github.djunicode.canteenapp.RequestObjects.SignUpRequest;
import io.github.djunicode.canteenapp.ResponseObjects.SignUpResponse;
import io.github.djunicode.canteenapp.RetrofitInterfaces.ApiInterface;

public class SignUpStudent extends BaseActivity {
    EditText username_view,pass_view,fname_view,lname_view,email_view,phone_view,div_view,dept_view,admission_year_view;
    Button signupButton;
    private ApiInterface apiInterface;
    String Rusername,Rfname,Rlname,Remail,Rphone,Rdiv,Rdept;
    int Radmission_year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        username_view=findViewById(R.id.signup_sap);
        pass_view=findViewById(R.id.signup_pass);
        fname_view=findViewById(R.id.signup_Fname);
        lname_view=findViewById(R.id.signup_Lname);
        email_view=findViewById(R.id.signup_email);
        phone_view=findViewById(R.id.signup_phone);
        div_view=findViewById(R.id.signup_div);
        dept_view=findViewById(R.id.signup_dept);
        admission_year_view=findViewById(R.id.signup_year);
        signupButton=findViewById(R.id.signupbutton);

        apiInterface = customRetrofit.create(ApiInterface.class);



    }
    public void signupclicked(View view){
        String username,pass,fname,lname,email,phone,div,dept;
        int admission_year;
        username=username_view.getText().toString();
        pass=pass_view.getText().toString();
        fname=fname_view.getText().toString();
        lname=lname_view.getText().toString();
        email=email_view.getText().toString();
        phone=phone_view.getText().toString();
        div=div_view.getText().toString();
        dept=dept_view.getText().toString();
        admission_year=Integer.parseInt(admission_year_view.getText().toString());

        SignUpRequest post=new SignUpRequest(username,pass,fname,lname,email,phone,div,dept,admission_year);

        Call<SignUpResponse> call = apiInterface.createPostSignUp(post);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(SignUpStudent.this, "Some error has occurred...", Toast.LENGTH_SHORT).show();
                    return;
                }
                SignUpResponse signUpResponse=response.body();
                Rusername=signUpResponse.getUsername();
                Rfname=signUpResponse.getFirst_name();
                Rlname=signUpResponse.getLast_name();
                Remail=signUpResponse.getEmail();
                Rphone=signUpResponse.getPhone_number();
                Rdiv=signUpResponse.getDivision();
                Rdept=signUpResponse.getDepartment();
                Radmission_year=signUpResponse.getAdmission_year();

                Toast.makeText(SignUpStudent.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignUpStudent.this, LoginActivity.class);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Toast.makeText(SignUpStudent.this, "Sign Up failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
