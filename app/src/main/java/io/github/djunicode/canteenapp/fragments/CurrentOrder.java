package io.github.djunicode.canteenapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import io.github.djunicode.canteenapp.OrderAdapter;
import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.models.MenuItem;

public class CurrentOrder extends Fragment {

    ArrayList<MenuItem> items;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.current_order,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpDummy();

        ListView list = (ListView)view.findViewById(R.id.list_current_order);
        list.setDivider(null);
        list.setDividerHeight(0);

        OrderAdapter madapter = new OrderAdapter(items,getActivity());

        list.setAdapter(madapter);
    }

    void setUpDummy(){
        items = new ArrayList<>();

        for(int i =0;i<5;i++){

            items.add(new MenuItem(1,10,0,10,"name","jain","10",true));

        }

    }

}
