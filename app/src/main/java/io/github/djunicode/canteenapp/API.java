package io.github.djunicode.canteenapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static Retrofit instance = null;
    public static String BASE_URL = "https://unicode-canteen.herokuapp.com/v1/";

    public static Retrofit getInstance(){

        if(instance == null){
            instance = new  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return instance;
    }


}
