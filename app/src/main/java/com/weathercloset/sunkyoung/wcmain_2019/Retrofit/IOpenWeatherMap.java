package com.weathercloset.sunkyoung.wcmain_2019.Retrofit;

import com.weathercloset.sunkyoung.wcmain_2019.Model.WeatherForecastResult;
import com.weathercloset.sunkyoung.wcmain_2019.Model.WeatherResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SunKyoung on 2019-05-02.
 */

public interface IOpenWeatherMap {
    @GET("weather")
    Observable<WeatherResult> getWeatherByLatLng(@Query("lat") String lat,
                                                 @Query("lon") String lng,
                                                 @Query("appid") String appid,
                                                 @Query("units") String unit,
                                                 @Query("lang") String lang
                                                 );

    @GET("forecast")
    Observable<WeatherForecastResult> getForecastWeatherByLatLng(@Query("lat") String lat,
                                                                 @Query("lon") String lng,
                                                                 @Query("appid") String appid,
                                                                 @Query("units") String unit
    );
}
