package io.github.djunicode.canteenapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import io.github.djunicode.canteenapp.fragments.Cart;
import io.github.djunicode.canteenapp.fragments.Menu;
import io.github.djunicode.canteenapp.fragments.Orders;
import io.github.djunicode.canteenapp.fragments.Profile;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    FragmentManager mFragmentManager;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFragmentManager=getSupportFragmentManager();

        bottomNav = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);


        FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();

        Fragment menu = new Menu();
        fragmentTransaction.add(R.id.fragment_placeholder,menu);
        fragmentTransaction.commit();
        setActionBarTitle("MENU");
    }



    public BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            switch (menuItem.getItemId()){

                case R.id.nav_menu:
                    Fragment menu = new Menu();
                    fragmentTransaction.replace(R.id.fragment_placeholder,menu);
                    fragmentTransaction.commit();
                    setActionBarTitle("MENU");
                    return  true;

                case R.id.nav_cart:
                    Fragment cart = new Cart();
                    fragmentTransaction.replace(R.id.fragment_placeholder,cart);
                    fragmentTransaction.commit();
                    setActionBarTitle("CART");
                    return true;

                case R.id.nav_order:
                    Fragment order = new Orders();
                    fragmentTransaction.replace(R.id.fragment_placeholder,order);
                    fragmentTransaction.commit();
                    setActionBarTitle("ORDERS");
                    return true;

                case R.id.nav_profile:
                    Fragment profile = new Profile();
                    fragmentTransaction.replace(R.id.fragment_placeholder,profile);
                    fragmentTransaction.commit();
                    setActionBarTitle("PROFILE");
                    return true;
            }



            return false;
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //money paid go to orders

        if(resultCode==1){

            try {
                bottomNav.setSelectedItemId(R.id.nav_order);
            }catch (IllegalStateException e){
                Log.e(TAG, "onActivityResult: ",e);
            }

            FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();

            Fragment order = new Orders();
            fragmentTransaction.add(R.id.fragment_placeholder,order);
            fragmentTransaction.commitAllowingStateLoss();
            setActionBarTitle("ORDERS");
        }
    }

    public void setActionBarTitle(String title){

        getSupportActionBar().setTitle(title);
    }
}
