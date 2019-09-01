package io.github.djunicode.canteenapp.fragments;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.djunicode.canteenapp.API;
import io.github.djunicode.canteenapp.GlobalData;
import io.github.djunicode.canteenapp.MainActivity;
import io.github.djunicode.canteenapp.MenuAdapter;
import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.RetrofitInterfaces.ApiInterface;
import io.github.djunicode.canteenapp.models.MenuItem;
import io.github.djunicode.canteenapp.models.MenuItemModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Menu extends Fragment {

    private List<MenuItem> menuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;
    private static final String TAG = "Menu";

    ApiInterface api = API.getInstance().create(ApiInterface.class);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareMenuData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.newmenu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated: Menu");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_menu);

        mAdapter = new MenuAdapter(menuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

    }
//
//    @Override
//    public void onResume() {
//        Log.i(TAG, "onResume: fasdf");
//        super.onResume();
//        if(GlobalData.getInstance().getAllMenuItems() == null) return;
//
//        recyclerView.setAdapter(null);
//        menuList.removeAll(menuList);
//        menuList.addAll(GlobalData.getInstance().getAllMenuItems());
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            Log.i(TAG, "onHiddenChanged: ");

            recyclerView.setAdapter(null);
//            menuList.removeAll(menuList);
            menuList.clear();
            menuList.addAll(GlobalData.getInstance().getAllMenuItems());
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void prepareMenuData() {

        final Call<List<MenuItem>> menuCall = api.getMenu();

        menuCall.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                Log.i(TAG, "onResponse: "+response.body());

                GlobalData.getInstance().setAllMenuItems(response.body());
                menuList.clear();
                menuList.addAll(response.body()) ;
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());

                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
