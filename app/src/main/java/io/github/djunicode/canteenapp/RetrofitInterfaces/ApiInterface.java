package io.github.djunicode.canteenapp.RetrofitInterfaces;

import java.util.List;

import io.github.djunicode.canteenapp.RequestObjects.SignInRequest;
import io.github.djunicode.canteenapp.ResponseObjects.SignInResponse;
import io.github.djunicode.canteenapp.models.MenuItem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("login/")
    Call<SignInResponse> createPostLogin(@Body SignInRequest signInRequest);


    @GET("menu/")
    Call<List<MenuItem>> getMenu();

}
