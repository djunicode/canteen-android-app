package io.github.djunicode.canteenapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.djunicode.canteenapp.R;

public class Orders extends Fragment {

    TabLayout tabs ;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.order_fragment,container,false);

        tabs = (TabLayout)view.findViewById(R.id.tabs);

        viewPager =(ViewPager)view.findViewById(R.id.viewpager);

        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);

        return view;
    }



    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return new PreviousOrders();

                case 1:
                    return new CurrentOrder();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch(position){
                case 0 : return "Previous Orders";
                case 1 : return "Current Order";
            }
            return "";
        }
    }
 }
