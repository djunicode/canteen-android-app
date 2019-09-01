package io.github.djunicode.canteenapp.RetrofitInterfaces;

import java.util.List;

import io.github.djunicode.canteenapp.models.MenuItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("menu/")
    Call<List<MenuItem>> getMenu();

}
