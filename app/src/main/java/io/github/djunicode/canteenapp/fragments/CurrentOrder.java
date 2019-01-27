package io.github.djunicode.canteenapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.djunicode.canteenapp.OrderAdapter;
import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.models.FoodItem;

public class CurrentOrder extends Fragment {

    ArrayList<FoodItem> items;


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

        OrderAdapter madapter = new OrderAdapter(items,getActivity());

        list.setAdapter(madapter);
    }

    void setUpDummy(){
        items = new ArrayList<>();

        for(int i =0;i<5;i++){

            items.add(new FoodItem("items "+i,20,2));


        }

    }

}
