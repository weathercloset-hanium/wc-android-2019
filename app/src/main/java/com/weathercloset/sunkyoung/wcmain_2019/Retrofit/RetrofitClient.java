package com.weathercloset.sunkyoung.wcmain_2019.Retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SunKyoung on 2019-05-02.
 */

public class RetrofitClient {
    private static Retrofit instance;
    public static Retrofit getInstance(){
        if(instance==null)
            instance = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return instance;
    }

}
