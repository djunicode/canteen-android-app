package io.github.djunicode.canteenapp.models;

import com.google.gson.annotations.SerializedName;

public class MenuItem {

    int id,price,quantity;

    @SerializedName("category")
    int categoryId;

    String name,options;

    @SerializedName("preparation_time")
    String time;

    boolean is_available;

    public MenuItem(int id, int price, int quantity, int categoryId, String name, String options, String time, boolean is_available) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.name = name;
        this.options = options;
        this.time = time;
        this.is_available = is_available;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getOptions() {
        return options;
    }

    public String getTime() {
        return time;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public int addOne(){
        quantity+=1;
        return quantity;
    }

    public int removeOne(){
        quantity-=1;
        return quantity;
    }

}
