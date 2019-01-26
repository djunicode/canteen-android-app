package io.github.djunicode.canteenapp.models;

import java.util.ArrayList;

public class FoodItem {

    String name;
    int price,quantity;


    public FoodItem(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
   }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getquantity() {
        return quantity;
    }
}
