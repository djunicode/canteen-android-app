package io.github.djunicode.canteenapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.djunicode.canteenapp.RequestObjects.Items;
import io.github.djunicode.canteenapp.RequestObjects.SendOrder;
import io.github.djunicode.canteenapp.ResponseObjects.OrderSentResponse;
import io.github.djunicode.canteenapp.ResponseObjects.ResponseItems;
import io.github.djunicode.canteenapp.RetrofitInterfaces.ApiInterface;
import io.github.djunicode.canteenapp.models.MenuItem;
import io.github.djunicode.canteenapp.models.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_SHARED_PREFS_KEY;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_STRING;

public class CheckOutActivity extends BaseActivity {

    List<MenuItem> items;
    ArrayList<Items> orderItemList;
    private ApiInterface apiInterface;
    ArrayList<ResponseItems> responseItemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);




        orderItemList = new ArrayList<>();
        responseItemsList = new ArrayList<>();
        apiInterface = customRetrofit.create(ApiInterface.class);

//        setUpDummy();

        ListView list = (ListView) findViewById(R.id.list_checkout);

        OrderAdapter madapter = new OrderAdapter(GlobalData.getInstance().getSelectedItems(), this);

        list.setAdapter(madapter);

        findViewById(R.id.confirm_checkout).setOnClickListener(new listener());
    }


    void setUpDummy() {
        items = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            items.add(new MenuItem(1,10,0,10,"name","jain","10",true));


        }
    }



    class listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            SharedPreferences tokenPrefs = getSharedPreferences(TOKEN_SHARED_PREFS_KEY, MODE_PRIVATE);
            String tokenValue = tokenPrefs.getString(TOKEN_STRING, null);
            Toast.makeText(CheckOutActivity.this,"TOKEN : "+tokenValue,Toast.LENGTH_SHORT).show();

            items = GlobalData.getInstance().getSelectedItems();
            for(int  i = 0;i<items.size();i++)
            {
                MenuItem menuItem = items.get(i);
                orderItemList.add(new Items(menuItem.getName(),menuItem.getQuantity(),"comment",menuItem.getPrice()));
            }


            SendOrder sendOrder = new SendOrder(2,false,"paytm",
                    "abc",null,orderItemList);

            Call<OrderSentResponse> call = apiInterface.placeOrder(tokenValue,sendOrder);
            call.enqueue(new Callback<OrderSentResponse>() {
                @Override
                public void onResponse(Call<OrderSentResponse> call, Response<OrderSentResponse> response)
                {
                    if(!response.isSuccessful()){
                        Toast.makeText(CheckOutActivity.this, "Some error has occurred..."+response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                        Log.d("CHECKOUT ERROR : ", "onResponse: " + response.errorBody().toString() );
                        return;
                    }
                    OrderSentResponse orderSentResponse = response.body();
                    responseItemsList = orderSentResponse.getItems();
                    //Toast.makeText(CheckOutActivity.this,"SIZE = "+responseItemsList.size(),Toast.LENGTH_SHORT).show();
                    for (int i=0;i<responseItemsList.size();i++)
                    {
                        ResponseItems responseItems = responseItemsList.get(i);
                        Log.d("DEBUGGING : ", "onClick: "+responseItems.getMenu_item()+"-->"+responseItems.getQuantity());
                    }
                }

                @Override
                public void onFailure(Call<OrderSentResponse> call, Throwable t) {
                    Toast.makeText(CheckOutActivity.this, "Order could not be placed", Toast.LENGTH_SHORT).show();
                    Log.d("CHECKOUT", "onFailure: " + t.getMessage() );
                }
            });

            setResult(1);
            finish();
        }
    }
}