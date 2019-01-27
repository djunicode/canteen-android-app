package io.github.djunicode.canteenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import io.github.djunicode.canteenapp.models.FoodItem;

public class CheckOutActivity extends AppCompatActivity {

    ArrayList<FoodItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);


        setUpDummy();

        ListView list = (ListView) findViewById(R.id.list_checkout);

        OrderAdapter madapter = new OrderAdapter(items, this);

        list.setAdapter(madapter);

        findViewById(R.id.confirm_checkout).setOnClickListener(new listener());
    }


    void setUpDummy() {
        items = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            items.add(new FoodItem("items " + i, 20, 2));


        }
    }

    class listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            setResult(1);
            finish();
        }
    }
}