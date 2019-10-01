package io.github.djunicode.canteenapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;
import android.content.SharedPreferences;
import io.github.djunicode.canteenapp.API;
import io.github.djunicode.canteenapp.GlobalData;
import io.github.djunicode.canteenapp.LoginActivity;
import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.ResponseObjects.ProfileResponse;
import io.github.djunicode.canteenapp.RetrofitInterfaces.ApiInterface;

import static android.content.Context.MODE_PRIVATE;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_SHARED_PREFS_KEY;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_STRING;

public class Profile extends Fragment {
    String Rusername,Rfname,Rlname,Remail,Rphone,Rdiv,Rdept;
    int Radmission_year;
    TextView name,email,sap,div,dept,year,phone;
    ApiInterface api = API.getInstance().create(ApiInterface.class);

    private static final String TAG = "profile";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        displayDetails();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name=view.findViewById(R.id.textView3);
        email=view.findViewById(R.id.textView8);
        sap=view.findViewById(R.id.textView4);
        phone=view.findViewById(R.id.textView6);
        dept=view.findViewById(R.id.department);
        div=view.findViewById(R.id.division);
        year=view.findViewById(R.id.yearofadmission);
        TextView logoutTextView = (TextView) view.findViewById(R.id.logoutText);
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        //Add api call for logout here
        SharedPreferences tokenPrefs = this.getActivity().getSharedPreferences(TOKEN_SHARED_PREFS_KEY, MODE_PRIVATE);
        tokenPrefs.edit().putString(TOKEN_STRING, null).apply();
        Intent intent = new Intent(this.getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.getActivity().finish();
        Toast.makeText(this.getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
    }


    private void displayDetails(){
        SharedPreferences tokenPrefs = getContext().getSharedPreferences(TOKEN_SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        String tokenValue = tokenPrefs.getString(TOKEN_STRING, null);
        Log.i(TAG,"api interface"+api);
        Log.d("token value",""+tokenValue);
        Log.d("token pref",""+tokenPrefs);
        final Call<ProfileResponse> createprofile= api.getProfile("token "+tokenValue);
        createprofile.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                Log.i(TAG, "response body "+response.body());
                Log.i(TAG,"response code::"+response.code());
                Log.i(TAG,"response message: "+response.message());
                Log.i(TAG,"response header: "+response.headers());
                Log.i(TAG,"response error body: "+response.errorBody());
                Log.i(TAG,"response isSuccessful: "+response.isSuccessful());
                if(!response.isSuccessful()){
                   Toast.makeText(getContext(), "Some error has occurred...", Toast.LENGTH_SHORT).show();

                 Log.d("profile load","failed...");
                return;
                }
                ProfileResponse profileResponse=response.body();
                Rusername=profileResponse.getUsername();
                Rfname=profileResponse.getFirst_name();
                Rlname=profileResponse.getLast_name();
                Remail=profileResponse.getEmail();
                Rphone=profileResponse.getPhone_number();
                Rdiv=profileResponse.getDivision();
                Rdept=profileResponse.getDepartment();
                Radmission_year=profileResponse.getAdmission_year();
                name.setText(""+Rfname+" "+Rlname);
                sap.setText(Rusername);
                email.setText(Remail);
                phone.setText(Rphone);
                dept.setText(Rdept);
                div.setText(Rdiv);
                year.setText(""+Radmission_year);
                Log.d("profile load","SUCCESS");
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

                Toast.makeText(getContext(), "Loading failed", Toast.LENGTH_SHORT).show();
                Log.d("profile load","FAILED");
            }
        });



    }

}
