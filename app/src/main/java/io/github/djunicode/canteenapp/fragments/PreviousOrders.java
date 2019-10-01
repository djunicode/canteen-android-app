package io.github.djunicode.canteenapp.fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.djunicode.canteenapp.API;
import io.github.djunicode.canteenapp.CheckOutActivity;
import io.github.djunicode.canteenapp.GlobalData;
import io.github.djunicode.canteenapp.OrderAdapter;
import io.github.djunicode.canteenapp.R;
import io.github.djunicode.canteenapp.RetrofitInterfaces.ApiInterface;
import io.github.djunicode.canteenapp.models.MenuItem;
import io.github.djunicode.canteenapp.models.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_SHARED_PREFS_KEY;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_STRING;

public class PreviousOrders extends Fragment {

    private static final String TAG = "PreviousOrders";

    ProgressDialog progressDialog;
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    List<MenuItem> items;
    List<Order> orders;
    SwipeRefreshLayout swipeRefreshLayout;
    ApiInterface api = API.getInstance().create(ApiInterface.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.previous_order,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        setUpDummy();


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        SharedPreferences tokenPrefs = getActivity().getSharedPreferences(TOKEN_SHARED_PREFS_KEY, MODE_PRIVATE);
        final String tokenValue = tokenPrefs.getString(TOKEN_STRING, null);


        Call<List<Order>> prevOrderCall = api.getPreviousOrders("token "+tokenValue);


        prevOrderCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
//
//                orders.clear();
//                orders.addAll(response.body()) ;

                orders = response.body();
//                adapter.notifyDataSetChanged();

                Log.i(TAG, "onResponse: "+orders.get(0).getItems());

                recycler =(RecyclerView)view.findViewById(R.id.recycler_prev);

                recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

                adapter = new PreviousAdapter();
                recycler.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getContext(),TAG+"  "+t.getMessage(),Toast.LENGTH_SHORT).show();

                Log.i(TAG, "onFailure: "+t.getMessage());

            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh_prev);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Call<List<Order>> prevOrderCall = api.getPreviousOrders("token "+tokenValue);


                prevOrderCall.enqueue(new Callback<List<Order>>() {
                                          @Override
                                          public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                                              orders = response.body();
                                              adapter.notifyDataSetChanged();

                                              Log.i(TAG, "onResponse: " + orders.get(0).getItems());


                                              swipeRefreshLayout.setRefreshing(false);
                                          }

                                          @Override
                                          public void onFailure(Call<List<Order>> call, Throwable t) {
                                              Toast.makeText(getContext(), TAG + "  " + t.getMessage(), Toast.LENGTH_SHORT).show();

                                              Log.i(TAG, "onFailure: " + t.getMessage());


                                              swipeRefreshLayout.setRefreshing(false);
                                          }

                                      });

            }
        });
    }

    private void setUp(View view){

        recycler =(RecyclerView)view.findViewById(R.id.recycler_prev);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new PreviousAdapter();
        recycler.setAdapter(adapter);
    }

    void setUpDummy(){
        items = new ArrayList<>();
        orders=new ArrayList<>();
        for(int i =0;i<3;i++){

            items.add(new MenuItem(1,10,0,10,"name","jain","10",true));

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
            holder.date.setText(currentOrder.getDate());
            holder.time.setText(currentOrder.getTime());
        }


        @Override
        public int getItemCount() {
            return orders.size();
        }


        class myViewHolder extends RecyclerView.ViewHolder{

            ListView listView;
            TextView date,time;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                listView=(ListView)itemView.findViewById(R.id.list_previous);
                date =(TextView) itemView.findViewById(R.id.date);
                time =(TextView) itemView.findViewById(R.id.time);

            }
        }
    }
}
