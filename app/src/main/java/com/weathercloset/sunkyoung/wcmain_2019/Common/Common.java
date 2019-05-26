package com.weathercloset.sunkyoung.wcmain_2019.Common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SunKyoung on 2019-05-02.
 */

public class Common {
    public static final String APP_ID = "";
    public static Location current_location = null;
    public static String convertUnixToDate(int dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd EEE MM yyyy");
        String formatted = sdf.format(date);
        return formatted;
    }
    public static String convertUnixToDay(int dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        String formatted = sdf.format(date);
        return formatted;
    }
//    public static String setWeatherIcon(int actualId, long sunrise, long sunset){
//        int id = actualId / 100;
//        String icon = "";
//        if(actualId == 800){
//            long currentTime = new Date().getTime();
//            if(currentTime>=sunrise && currentTime<sunset) {
//                icon = "&#xf00d;";
//            } else {
//                icon = "&#xf02e;";
//            }
//        } else {
//            switch(id) {
//                case 2 : icon = "&#xf01e;";
//                    break;
//                case 3 : icon = "&#xf01c;";
//                    break;
//                case 7 : icon = "&#xf014;";
//                    break;
//                case 8 : icon = "&#xf013;";
//                    break;
//                case 6 : icon = "&#xf01b;";
//                    break;
//                case 5 : icon = "&#xf019;";
//                    break;
//            }
//        }
//        return icon;
//    }
}
