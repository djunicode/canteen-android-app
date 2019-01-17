package io.github.djunicode.canteenapp.models;

import java.util.ArrayList;

public class Order {

    String date,time,id;
    ArrayList<FoodItem> items;

    public Order(String date, String time, String id,
            ArrayList<FoodItem> items) {
        this.date = date;
        this.time = time;
        this.id = id;
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }
}
