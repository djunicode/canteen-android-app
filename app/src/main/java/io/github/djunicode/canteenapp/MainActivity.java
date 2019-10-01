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
//import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import io.github.djunicode.canteenapp.models.MenuItem;
import io.github.djunicode.canteenapp.RetrofitInterfaces.ApiInterface;
import io.github.djunicode.canteenapp.fragments.Cart;
import io.github.djunicode.canteenapp.fragments.Menu;
import io.github.djunicode.canteenapp.fragments.Orders;
import io.github.djunicode.canteenapp.fragments.Profile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    BottomNavigationView bottomNav;
    FragmentManager mFragmentManager;
    Fragment menu,cart,order,profile;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: MainAcitvity");
        setContentView(R.layout.activity_main);


        mFragmentManager=getSupportFragmentManager();

        bottomNav = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);

        menu = new Menu();
        cart = new Cart();
        order = new Orders();
        profile = new Profile();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction()
                                                                .add(R.id.fragment_placeholder,menu)
                                                                .add(R.id.fragment_placeholder,cart)
                                                                .add(R.id.fragment_placeholder,order)
                                                                .add(R.id.fragment_placeholder,profile);

//        fragmentTransaction.add(R.id.fragment_placeholder,menu);
        fragmentTransaction.show(menu);
        fragmentTransaction.hide(cart);
        fragmentTransaction.hide(order);
        fragmentTransaction.hide(profile);

        fragmentTransaction.commit();
        setActionBarTitle("MENU");
    }



    public BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull android.view.MenuItem menuItem) {

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            switch (menuItem.getItemId()){

                case R.id.nav_menu:
//                    fragmentTransaction.replace(R.id.fragment_placeholder,menu);

                    fragmentTransaction.show(menu);
                    fragmentTransaction.hide(cart);
                    fragmentTransaction.hide(order);
                    fragmentTransaction.hide(profile);

                    fragmentTransaction.commit();
                    setActionBarTitle("MENU");
                    return  true;

                case R.id.nav_cart:
//                    fragmentTransaction.replace(R.id.fragment_placeholder,cart);


                    fragmentTransaction.hide(menu);
                    fragmentTransaction.show(cart);
                    fragmentTransaction.hide(order);
                    fragmentTransaction.hide(profile);

                    fragmentTransaction.commit();
                    setActionBarTitle("CART");
                    return true;

                case R.id.nav_order:
//                    fragmentTransaction.replace(R.id.fragment_placeholder,order);


                    fragmentTransaction.hide(menu);
                    fragmentTransaction.hide(cart);
                    fragmentTransaction.show(order);
                    fragmentTransaction.hide(profile);

                    fragmentTransaction.commit();
                    setActionBarTitle("ORDERS");
                    return true;

                case R.id.nav_profile:
//                    fragmentTransaction.replace(R.id.fragment_placeholder,profile);


                    fragmentTransaction.hide(menu);
                    fragmentTransaction.hide(cart);
                    fragmentTransaction.hide(order);
                    fragmentTransaction.show(profile);

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

        super.onActivityResult(requestCode,resultCode,data);
//        if(resultCode==1){
//
//            try {
////                bottomNav.setSelectedItemId(R.id.nav_order);
//
//                FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();
//
//                fragmentTransaction.add(new Orders(), null);
//                fragmentTransaction.commitAllowingStateLoss();
//
////                fragmentTransaction.commit();
//                setActionBarTitle("ORDERS");
//
//            }catch (IllegalStateException e){
//                Log.e(TAG, "onActivityResult: ",e);
//            }
//
//
////            Fragment order = new Orders();
////            fragmentTransaction.add(R.id.fragment_placeholder,order);
////            fragmentTransaction.commitAllowingStateLoss();
////            setActionBarTitle("ORDERS");
//        }
    }

    public void setActionBarTitle(String title){

        getSupportActionBar().setTitle(title);
    }


}
