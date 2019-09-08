package io.github.djunicode.canteenapp.RetrofitInterfaces;

import java.util.List;

import io.github.djunicode.canteenapp.RequestObjects.ChangePassword;
import io.github.djunicode.canteenapp.RequestObjects.SignInRequest;
import io.github.djunicode.canteenapp.RequestObjects.SignUpRequest;
import io.github.djunicode.canteenapp.ResponseObjects.SignInResponse;
import io.github.djunicode.canteenapp.ResponseObjects.SignUpResponse;
import io.github.djunicode.canteenapp.models.MenuItem;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("auth/token/login/")
    Call<SignInResponse> createPostLogin(@Body SignInRequest signInRequest);


    @GET("menu/")
    Call<List<MenuItem>> getMenu();


    @POST("auth/users/set_password/")
    Call<ResponseBody> changePassword(@Body ChangePassword changePassword);

    @POST("student_signup/")
    Call<SignUpResponse>createPostSignUp(@Body SignUpRequest signUpRequest);

}
