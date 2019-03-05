package io.github.djunicode.canteenapp.RequestObjects;

import java.util.ArrayList;

public class SendOrder {

    private Integer user;
    private Boolean is_fulfilled;
    private String payment_choices;
    private String transaction_id;
    private String time_scheduled;
    private ArrayList<Items> items;

    public SendOrder(Integer user, Boolean is_fulfilled, String payment_choices, String transaction_id, String time_scheduled, ArrayList<Items> items) {
        this.user = user;
        this.is_fulfilled = is_fulfilled;
        this.payment_choices = payment_choices;
        this.transaction_id = transaction_id;
        this.time_scheduled = time_scheduled;
        this.items = items;
    }
}
