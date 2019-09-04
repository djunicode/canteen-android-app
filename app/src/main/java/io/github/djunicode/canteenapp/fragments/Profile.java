package io.github.djunicode.canteenapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import io.github.djunicode.canteenapp.LoginActivity;
import io.github.djunicode.canteenapp.R;

import static android.content.Context.MODE_PRIVATE;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_SHARED_PREFS_KEY;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_STRING;

public class Profile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

}
