package com.weathercloset.sunkyoung.wcmain_2019;


import android.text.Spanned;


public class ListViewForecast {
    private String dt ;
    private Spanned weathericon ;
    private String temp ;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Spanned getWeathericon() {
        return weathericon;
    }

    public void setWeathericon(Spanned weathericon) {
        this.weathericon = weathericon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
