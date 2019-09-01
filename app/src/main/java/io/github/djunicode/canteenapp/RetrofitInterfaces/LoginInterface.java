package io.github.djunicode.canteenapp.RetrofitInterfaces;

import io.github.djunicode.canteenapp.RequestObjects.SignInRequest;
import io.github.djunicode.canteenapp.ResponseObjects.SignInResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {
    @POST("login/")
    Call<SignInResponse> createPostLogin(@Body SignInRequest signInRequest);
}
