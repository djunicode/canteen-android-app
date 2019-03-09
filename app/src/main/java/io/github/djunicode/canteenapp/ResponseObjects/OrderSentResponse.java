package io.github.djunicode.canteenapp.ResponseObjects;

import java.util.ArrayList;

public class OrderSentResponse {

    private Integer id;
    private Integer user;
    private Boolean is_fulfilled;
    private String payment_choices;
    private String status;
    private String transaction_id;
    private String time_issued;
    private String time_sheduled;
    private String time_prepared;
    private String time_delivered;
    private ArrayList<ResponseItems> items;

    public Integer getId() {
        return id;
    }

    public Integer getUser() {
        return user;
    }

    public Boolean getIs_fulfilled() {
        return is_fulfilled;
    }

    public String getPayment_choices() {
        return payment_choices;
    }

    public String getStatus() {
        return status;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getTime_issued() {
        return time_issued;
    }

    public String getTime_sheduled() {
        return time_sheduled;
    }

    public String getTime_prepared() {
        return time_prepared;
    }

    public String getTime_delivered() {
        return time_delivered;
    }

    public ArrayList<ResponseItems> getItems() {
        return items;
    }
}
