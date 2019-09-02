package io.github.djunicode.canteenapp.fragments;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.djunicode.canteenapp.CheckOutActivity;
import io.github.djunicode.canteenapp.GlobalData;
import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.models.MenuItem;

public class Cart extends Fragment {

    private static final String TAG = "Cart";

    List<MenuItem> items = new ArrayList<>();
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

        items.addAll(GlobalData.getInstance().getSelectedItems());
//        setUpDummy();


        adapter.notifyDataSetChanged();

        txtItemTotal=(TextView)view.findViewById(R.id.item_total_amount_cart);
        txtTotal=(TextView)view.findViewById(R.id.total_price_cart);
        txtTax=(TextView)view.findViewById(R.id.tax_amount_cart);


        calculateTotal();


        view.findViewById(R.id.pay_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getActivity() , CheckOutActivity.class);

                startActivityForResult(intent,0);
            }
        });

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        items = GlobalData.getInstance().getSelectedItems();
//        adapter.notifyDataSetChanged();
//    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            Log.i(TAG, "onHiddenChanged: ");

            myRecyclerView.setAdapter(null);
            items.clear();
            items.addAll(GlobalData.getInstance().getSelectedItems());
            myRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    void setUpDummy(){
        items = new ArrayList<>();

        for(int i =0;i<7;i++){

            items.add(new MenuItem(1,10,0,10,"name","jain","10",true));


        }

        adapter.notifyDataSetChanged();
    }

    void populateData(){

    }

    void calculateTotal(){

        total=0.0;
        itemTotal=0.0;
        tax=0.0;

        for (int i=0;i<items.size();i++){

            itemTotal+=items.get(i).getPrice()*items.get(i).getQuantity();

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

            MenuItem current = items.get(position);

            holder.name.setText(current.getName());
            holder.quantity.setText(Integer.toString(current.getQuantity()));
            holder.price.setText(Integer.toString(current.getPrice()));


        }

        @Override
        public int getItemCount() {
            return items.size();
        }


        public class myViewHolder extends RecyclerView.ViewHolder{

            TextView name,price,quantity,minus,plus;
            Spinner customize;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                name=(TextView)itemView.findViewById(R.id.title_cart);
                price=(TextView)itemView.findViewById(R.id.price_cart);
                quantity=(TextView)itemView.findViewById(R.id.quantity_cart);
                minus=(TextView)itemView.findViewById(R.id.minus_cart);
                plus=(TextView)itemView.findViewById(R.id.plus_cart);

                customize = (Spinner)itemView.findViewById(R.id.customize);

                ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.spinner_values,android.R.layout.simple_spinner_item);

                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                customize.setAdapter(spinnerAdapter);

                customize.setSelection(1);

                customize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        // An item was selected. You can retrieve the selected item using
                        // parent.getItemAtPosition(pos)

                        //this to change the size of text dynamically
                        ((TextView) parent.getChildAt(0)).setTextSize(10);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                listener mlistener =new listener();
                minus.setOnClickListener(mlistener);
                plus.setOnClickListener(mlistener);
            }

            class listener implements View.OnClickListener {

                @Override
                public void onClick(View v) {


                    Log.d("clicked ",Integer.toString(getAdapterPosition()));

                    MenuItem current = items.get(getAdapterPosition());
                    int q;
                    switch(v.getId()){

                        case R.id.plus_cart:
                           q= current.getQuantity();
                           current.setQuantity(++q);
                           break;

                        case R.id.minus_cart:
                            q= current.getQuantity();
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
