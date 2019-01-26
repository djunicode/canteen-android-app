package io.github.djunicode.canteenapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.models.MenuItemModel;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<MenuItemModel> menuList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, time;
        public Button Add;
        public GridLayout gridadd;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
            price = (TextView) view.findViewById(R.id.price);
            Add=view.findViewById(R.id.add);
            gridadd=view.findViewById(R.id.gridadd);
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Add.setVisibility(View.INVISIBLE);
                    gridadd.setVisibility(View.VISIBLE);
                }
            });


        }
    }


    public MenuAdapter(List<MenuItemModel> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MenuItemModel movie = menuList.get(position);
        holder.title.setText(movie.getTitle());
        holder.time.setText(movie.getGenre());
        holder.price.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return menuList.size();

    }
}