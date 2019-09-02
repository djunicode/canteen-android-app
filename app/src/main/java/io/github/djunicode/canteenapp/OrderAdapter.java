package io.github.djunicode.canteenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.djunicode.canteenapp.models.MenuItem;

//This is the adapter for listview for orders in both previous and current order fragment

public class OrderAdapter extends BaseAdapter {

    List<MenuItem> items;
    Context context;

    public OrderAdapter(List<MenuItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.food_listitem, parent,
                    false);
        }


        TextView name = (TextView) convertView.findViewById(R.id.name_order);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity_order);
        TextView price = (TextView) convertView.findViewById(R.id.price_order);

        MenuItem item = (MenuItem) getItem(position);


        name.setText(item.getName());
        quantity.setText(" X " +Integer.toString(item.getQuantity()));
        price.setText(Integer.toString(item.getPrice()));

        return convertView;
    }
}
