package com.weathercloset.sunkyoung.wcmain_2019.Model;

import java.util.List;

public class WeatherForecastResult {
    public String cod;
    public double message;
    public int cnt;
    public List<MyList> list;
    public City city;

    public static String setWeatherIcon(int actualId){
        int id = actualId / 100;
        String icon = "";
        switch(id) {
                case 2 : icon = "&#xf01e;";
                    break;
                case 3 : icon = "&#xf01c;";
                    break;
                case 7 : icon = "&#xf014;";
                    break;
                case 8 : icon = "&#xf013;";
                    break;
                case 6 : icon = "&#xf01b;";
                    break;
                case 5 : icon = "&#xf019;";
                    break;
            }

        return icon;
    }
}
