package io.github.djunicode.canteenapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.models.FoodItem;

public class Cart extends Fragment {


    ArrayList<FoodItem> items;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter adapter;
    Double total=0.0,itemTotal=0.0,tax=0.0;

    TextView txtTotal,txtItemTotal,txtTax;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.cart,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_cart);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration  dividerItemDecoration = new DividerItemDecoration(myRecyclerView.getContext(),
                layoutManager.getOrientation());
        myRecyclerView.addItemDecoration(dividerItemDecoration);


        adapter = new CartAdapter();
        myRecyclerView.setAdapter(adapter);

        setUpDummy();




        txtItemTotal=(TextView)view.findViewById(R.id.item_total_amount_cart);
        txtTotal=(TextView)view.findViewById(R.id.total_price_cart);
        txtTax=(TextView)view.findViewById(R.id.tax_amount_cart);


        calculateTotal();

    }


    void setUpDummy(){
        items = new ArrayList<>();

        for(int i =0;i<5;i++){

            items.add(new FoodItem("items "+i,20,2));


        }

        adapter.notifyDataSetChanged();
    }



    void calculateTotal(){

        total=0.0;
        itemTotal=0.0;
        tax=0.0;

        for (int i=0;i<items.size();i++){

            itemTotal+=items.get(i).getPrice()*items.get(i).getquantity();

        }

        tax= 0.3*itemTotal;
        total=itemTotal+tax;

        txtTax.setText(Double.toString(tax));
        txtTotal.setText(Double.toString(total));
        txtItemTotal.setText(Double.toString(itemTotal));
    }






    public class CartAdapter extends RecyclerView.Adapter< CartAdapter.myViewHolder> {


        @NonNull
        @Override
        public CartAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item,viewGroup,false);

            return new myViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CartAdapter.myViewHolder holder, int position) {

            FoodItem current = items.get(position);

            holder.name.setText(current.getName());
            holder.quantity.setText(Integer.toString(current.getquantity()));
            holder.price.setText(Integer.toString(current.getPrice()));


        }

        @Override
        public int getItemCount() {
            return items.size();
        }


        public class myViewHolder extends RecyclerView.ViewHolder{

            TextView name,price,quantity,minus,plus;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                name=(TextView)itemView.findViewById(R.id.title_cart);
                price=(TextView)itemView.findViewById(R.id.price_cart);
                quantity=(TextView)itemView.findViewById(R.id.quantity_cart);
                minus=(TextView)itemView.findViewById(R.id.minus_cart);
                plus=(TextView)itemView.findViewById(R.id.plus_cart);


                listener mlistener =new listener();
                minus.setOnClickListener(mlistener);
                plus.setOnClickListener(mlistener);
            }

            class listener implements View.OnClickListener {

                @Override
                public void onClick(View v) {


                    Log.d("clicked ",Integer.toString(getAdapterPosition()));

                    FoodItem current = items.get(getAdapterPosition());
                    int q;
                    switch(v.getId()){

                        case R.id.plus_cart:
                           q= current.getquantity();
                           current.setQuantity(++q);
                           break;

                        case R.id.minus_cart:
                            q= current.getquantity();
                            if(q>1) {
                                current.setQuantity(--q);  //TODO handle quantity zero
                            }else{
                                return;
                            }
                            break;
                    }

                    adapter.notifyDataSetChanged();
                    calculateTotal();

                }
            }
        }
    }
}
