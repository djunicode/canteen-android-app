package io.github.djunicode.canteenapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Order {

    String date,time;

    @SerializedName("items")
    List<MenuItem> items;

    @SerializedName("id")
    String id;

    @SerializedName("time_issued")
    String dateTime;

//                    "id": 1,
//                    "user": 1,
//                    "is_fulfilled": true,
//                    "time_issued": "2019-09-05T16:26:42.694732+05:30",
//                    "time_sheduled": "2019-09-05T16:26:38+05:30",
//                    "time_prepared": "2019-09-05T16:26:39+05:30",
//                    "time_delivered": "2019-09-05T16:26:41+05:30",
//                    "items": []
//



    public Order(String date, String time, String id,
                 List<MenuItem> items) {
        this.date = date;
        this.time = time;
        this.id = id;
        this.items = items;
    }

    public String getDate() {
        return dateTime.substring(0,10);
    }

    public String getTime() {
        return dateTime.substring(11,19);
    }

    public String getId() {
        return id;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
