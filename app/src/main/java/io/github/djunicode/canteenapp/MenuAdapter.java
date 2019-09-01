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
import io.github.djunicode.canteenapp.models.MenuItem;
import io.github.djunicode.canteenapp.models.MenuItemModel;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

       List<MenuItem> SelectedItems = GlobalData.getInstance().getSelectedItems();
      private List<MenuItem> menuList;
    int q;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, time , quantity , plus ,minus;
        public Button Add;
        public GridLayout gridadd;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
            price = (TextView) view.findViewById(R.id.price);
            Add=view.findViewById(R.id.add_menu);
            gridadd=view.findViewById(R.id.gridadd);

            plus = view.findViewById(R.id.plus_menu);
            minus = view.findViewById(R.id.minus_menu);
            quantity = view.findViewById(R.id.quantity_menu);

            mListener listener = new mListener();

            Add.setOnClickListener(listener);
            plus.setOnClickListener(listener);
            minus.setOnClickListener(listener);

            q=1;

        }


        public class mListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                MenuItem current = menuList.get(getAdapterPosition());

                switch (v.getId()){

                    case R.id.plus_menu:
                        current.addOne();
                        quantity.setText(Integer.toString(current.getQuantity()));
                        break;


                    case R.id.minus_menu:
                        current.removeOne();
                        if(current.getQuantity()>0){
                            quantity.setText(Integer.toString(current.getQuantity()));
                        }else{
                            Add.setVisibility(View.VISIBLE);
                            gridadd.setVisibility(View.GONE);
                            SelectedItems.remove(current);
                        }
                        break;

                    case R.id.add_menu:
                        current.addOne();
                        SelectedItems.add(current);
                        Add.setVisibility(View.GONE);
                        gridadd.setVisibility(View.VISIBLE);
                        quantity.setText(Integer.toString(current.getQuantity()));
                        break;

                }
            }
        }

    }


    public MenuAdapter(List<MenuItem> menuList) {
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
        MenuItem movie = menuList.get(position);

        holder.title.setText(movie.getName());
        holder.time.setText(movie.getTime());
        holder.price.setText(Integer.toString(movie.getPrice()));

        if(movie.getQuantity()>0){

            holder.Add.setVisibility(View.GONE);
            holder.gridadd.setVisibility(View.VISIBLE);
            holder.quantity.setText(Integer.toString(movie.getQuantity()));
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();

    }


}