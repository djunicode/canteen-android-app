package io.github.djunicode.canteenapp.RequestObjects;

public class Items {

    private String menu_item;
    private Integer quantity;
    private String comment;
    private Integer price;

    public Items(String menu_item, Integer quantity, String comment, Integer price) {
        this.menu_item = menu_item;
        this.quantity = quantity;
        this.comment = comment;
        this.price = price;
    }
}
