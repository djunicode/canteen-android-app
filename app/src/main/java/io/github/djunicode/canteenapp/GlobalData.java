package io.github.djunicode.canteenapp;

import java.util.ArrayList;
import java.util.List;

import io.github.djunicode.canteenapp.models.MenuItem;

public class GlobalData {

    private static GlobalData instance = null;
    public List<MenuItem> SelectedItems;
    public List<MenuItem> allMenuItems;
    protected GlobalData() {
        SelectedItems = new ArrayList<MenuItem>();
    }

    public static GlobalData getInstance() {
        if(instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    void addItem(MenuItem item){
        item.setQuantity(1);
        SelectedItems.add(item);
    }

    public List<MenuItem> getSelectedItems(){
        return SelectedItems;
    }


    public List<MenuItem> getAllMenuItems(){
        return allMenuItems;
    }

    public void setAllMenuItems(List<MenuItem> allMenuItems) {
        this.allMenuItems = allMenuItems;
    }
}
