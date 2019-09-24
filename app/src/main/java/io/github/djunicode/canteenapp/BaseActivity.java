package io.github.djunicode.canteenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.github.djunicode.canteenapp.ResponseObjects.SignInResponse;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_SHARED_PREFS_KEY;
import static io.github.djunicode.canteenapp.LoginActivity.TOKEN_STRING;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    String tokenReturn;
    protected Retrofit customRetrofit;
    protected Boolean addToken = true;
    private String toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toast = this.getIntent().getStringExtra("toastMessage");
        if (toast != null) {
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
        }

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                Response response = chain.proceed(request);

                Log.i(TAG, "intercept: "+response.body().toString());

                if(response.code() == 401){
                    removeToken();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class).putExtra(
                            "toastMessage", "Security Credentials Invalid"
                    );
                    startActivity(intent);
                    return response;
                }
                return response;
            }
        });
        customRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://unicode-canteen.herokuapp.com/v1/")
                .client(httpClient.build())
                .build();
    }

    public void removeToken() {
        saveToken(null);
    }

    public void saveToken(String token) {

        SharedPreferences tokenPrefs = getSharedPreferences(TOKEN_SHARED_PREFS_KEY, MODE_PRIVATE);
        tokenPrefs.edit().putString(TOKEN_STRING, token).apply();
    }

    public String retrieveToken(){
        SharedPreferences tokenPrefs = getSharedPreferences(TOKEN_SHARED_PREFS_KEY, MODE_PRIVATE);
        tokenReturn = tokenPrefs.getString(TOKEN_STRING, null);
        return tokenReturn;
    }
}
