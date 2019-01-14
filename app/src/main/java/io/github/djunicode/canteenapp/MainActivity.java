package io.github.djunicode.canteenapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import io.github.djunicode.canteenapp.fragments.Cart;
import io.github.djunicode.canteenapp.fragments.Orders;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFragmentManager=getSupportFragmentManager();

        bottomNav = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);
        bottomNav.setSelectedItemId(R.id.nav_cart);

        FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();

        Fragment cart = new Cart();
        fragmentTransaction.add(R.id.fragment_placeholder,cart);
        fragmentTransaction.commit();
    }



    public BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            switch (menuItem.getItemId()){

                case R.id.nav_menu:
                    return  true;

                case R.id.nav_cart:
                    Fragment cart = new Cart();
                    fragmentTransaction.replace(R.id.fragment_placeholder,cart);
                    fragmentTransaction.commit();
                    return true;

                case R.id.nav_order:
                    Fragment order = new Orders();
                    fragmentTransaction.replace(R.id.fragment_placeholder,order);
                    fragmentTransaction.commit();
                    return true;

                case R.id.nav_profile:
                    return true;
            }



            return false;
        }
    };
}
