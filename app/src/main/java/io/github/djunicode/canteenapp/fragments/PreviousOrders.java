package io.github.djunicode.canteenapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import io.github.djunicode.canteenapp.models.Order;

public class PreviousOrders extends Fragment {

    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    ArrayList<FoodItem> items;
    ArrayList<Order> orders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.previous_order,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpDummy();

        recycler =(RecyclerView)view.findViewById(R.id.recycler_prev);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new PreviousAdapter();
        recycler.setAdapter(adapter);
    }

    void setUpDummy(){
        items = new ArrayList<>();
        orders=new ArrayList<>();
        for(int i =0;i<3;i++){

            items.add(new FoodItem("items "+i,20,2));

            orders.add(new Order("today","12:00","1",items));
        }




         //adapter.notifyDataSetChanged();
    }

    class PreviousAdapter extends RecyclerView.Adapter<PreviousAdapter.myViewHolder>{

        @NonNull
        @Override
        public PreviousAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item,viewGroup,false);

            return new myViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

            Order currentOrder = orders.get(position);


            OrderAdapter listAdapter= new OrderAdapter(currentOrder.getItems(),getActivity());

            holder.listView.setAdapter(listAdapter);
        }


        @Override
        public int getItemCount() {
            return items.size();
        }


        class myViewHolder extends RecyclerView.ViewHolder{

            ListView listView;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                listView=(ListView)itemView.findViewById(R.id.list_previous);
            }
        }
    }
}
