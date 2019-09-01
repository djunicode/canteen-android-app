package io.github.djunicode.canteenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.djunicode.canteenapp.models.FoodItem;
import io.github.djunicode.canteenapp.models.MenuItem;

public class CheckOutActivity extends AppCompatActivity {

    List<MenuItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);


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
            setResult(1);
            finish();
        }
    }
}