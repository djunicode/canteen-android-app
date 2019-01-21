package io.github.djunicode.canteenapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.djunicode.canteenapp.MenuAdapter;
import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.models.MenuItemModel;

public class Menu extends Fragment {

    private List<MenuItemModel> menuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.newmenu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_menu);

        mAdapter = new MenuAdapter(menuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        prepareMenuData();
    }


    private void prepareMenuData() {
        MenuItemModel menu = new MenuItemModel("Cheese Dosa", "10-20 Mins", "35");
        menuList.add(menu);
        menu = new MenuItemModel("Cheese Dosa", "10-20 Mins", "35");
        menuList.add(menu);
        menu = new MenuItemModel("Cheese Dosa", "10-20 Mins", "35");
        menuList.add(menu);
        menu = new MenuItemModel("Cheese Dosa", "10-20 Mins", "35");
        menuList.add(menu);
        menu = new MenuItemModel("Cheese Dosa", "10-20 Mins", "35");
        menuList.add(menu);

        mAdapter.notifyDataSetChanged();
    }
}
