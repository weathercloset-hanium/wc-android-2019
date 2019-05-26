package com.weathercloset.sunkyoung.wcmain_2019;

/**
 * Created by SunKyoung on 2019-03-10.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.weathercloset.sunkyoung.wcmain_2019.Common.Common;
import com.weathercloset.sunkyoung.wcmain_2019.Model.WeatherForecastResult;
import com.weathercloset.sunkyoung.wcmain_2019.Model.WeatherResult;
import com.weathercloset.sunkyoung.wcmain_2019.Retrofit.IOpenWeatherMap;
import com.weathercloset.sunkyoung.wcmain_2019.Retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.support.v7.content.res.AppCompatResources.getDrawable;


public class Tab1 extends Fragment {
    private Activity ctx;
    ImageButton BtnLocation;
    TextView detailsField, currentTemperatureField, humidity_field, weatherIcon, updatedField, wind_field;
 //   ProgressBar loading;
 //   LinearLayout panel;
    ListView listforecastview;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap nService;
    Calendar calendar = Calendar.getInstance();
    Typeface weatherFont;
 //   String city = "Seoul, KR";

    static Tab1 instance;

    public static Tab1 getInstance() {
        if (instance == null)
            instance = new Tab1();
        return instance;

    }

    public Tab1() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        nService = retrofit.create(IOpenWeatherMap.class);
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            ctx = (Activity) context;
        }

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(ctx.getAssets(), "fonts/weather.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
//
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        ListView listview;
        ListViewAdapter adapter;


        // Adapter 생성
        adapter = new ListViewAdapter();
//        listViewForecastAdapter = new ListViewForecastAdapter(ctx, );

        // 리스트뷰 참조 및 Adapter달기
        listview = v.findViewById(R.id.listview1);
        listview.setAdapter(adapter);
        listforecastview = v.findViewById(R.id.listforecastview);


        // 첫 번째 아이템 추가.
        adapter.addItem(getDrawable(ctx, R.drawable.cardigan_col),
                getDrawable(ctx, R.drawable.jumper_col),getDrawable(ctx, R.drawable.coat_col),
                getDrawable(ctx, R.drawable.coat),getDrawable(ctx, R.drawable.cardigan),
                "OUTER") ;
        // 두 번째 아이템 추가.
        adapter.addItem(getDrawable(ctx, R.drawable.shirt_col),
                getDrawable(ctx, R.drawable.tshirt_col),getDrawable(ctx, R.drawable.hoodie_col),
                getDrawable(ctx, R.drawable.hoodie),getDrawable(ctx, R.drawable.tshirt),
                "TOP") ;
        // 세 번째 아이템 추가.
        adapter.addItem(getDrawable(ctx, R.drawable.dungarees_col),
                getDrawable(ctx, R.drawable.trousers_col),getDrawable(ctx, R.drawable.shorts_col),
                getDrawable(ctx, R.drawable.dungarees),getDrawable(ctx, R.drawable.trousers),
                "BOTTOM") ;

        // 날씨
//        cityField = (TextView) v.findViewById(R.id.city_field);
        updatedField = (TextView) v.findViewById(R.id.updated_field);
        detailsField = (TextView) v.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) v.findViewById(R.id.current_temperature_field);
        humidity_field = (TextView) v.findViewById(R.id.humidity_field);
        weatherIcon = (TextView) v.findViewById(R.id.weather_icon);
//        weatherFont = ResourcesCompat.getFont(ctx, R.font.weather);
//        weatherFont = Typeface.createFromAsset(ctx.getAssets(), "fonts/weathertherFont = getResources().getFont(R.font.weather);
        weatherIcon.setTypeface(weatherFont);
        wind_field = (TextView) v.findViewById(R.id.wind_field);

//        panel = (LinearLayout) v.findViewById(R.id.panel);
//        loading = (ProgressBar) v.findViewById(R.id.loading);
        BtnLocation = v.findViewById(R.id.btn_location);
//
//        BtnLocation.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if ( ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                }
//                else{
//                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    String provider = location.getProvider();
//                    double longitude = location.getLongitude();
//                    double latitude = location.getLatitude();
//                    double altitude = location.getAltitude();
//
//                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                            1000, 1,
//                            gpsLocationListener);
//                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                            1000,
//                            1,
//                            gpsLocationListener);
//                }
//            }
//        });

        getWeatherInformation();
        getForecastWeatherInformation();

//        taskLoadUp(city);
        return v;
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void getForecastWeatherInformation() {
        compositeDisposable.add(nService.getForecastWeatherByLatLng(
                String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecastResult>() {
                    @Override
                    public void accept(WeatherForecastResult weatherForecastResult) throws Exception {
                        displayForecastWeather(weatherForecastResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error",""+throwable.getMessage());
                    }
                })
        );
    }

    private void displayForecastWeather(WeatherForecastResult weatherForecastResult) {

        ListViewForecastAdapter listViewForecastAdapter;
        listViewForecastAdapter = new ListViewForecastAdapter(ctx, weatherForecastResult);
        listforecastview.setAdapter(listViewForecastAdapter);

        for(int i=0; i<weatherForecastResult.cnt; i++){

            String dt_diff = String.valueOf(Common.convertUnixToDate(weatherForecastResult.list.get(0).dt));
            if(dt_diff != String.valueOf(Common.convertUnixToDate(weatherForecastResult.list.get(i).dt))){
                listViewForecastAdapter.addItem(
                        String.valueOf(Common.convertUnixToDay(weatherForecastResult.list.get(i).dt)),
                        weatherForecastResult.setWeatherIcon(weatherForecastResult.list.get(i).weather.get(0).getId()),
                        String.valueOf(weatherForecastResult.list.get(i).main.getTemp_min())+" / " +String.valueOf(weatherForecastResult.list.get(i).main.getTemp_max())
                );
                dt_diff = String.valueOf(Common.convertUnixToDate(weatherForecastResult.list.get(i).dt));
            }

        }

    }

    private void getWeatherInformation() {
        compositeDisposable.add(nService.getWeatherByLatLng(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric","kr").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>(){

                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        //load information
//                        cityField.setText(weatherResult.getName());
                        detailsField.setText(weatherResult.getName());
                        currentTemperatureField.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());
                        updatedField.setText(Common.convertUnixToDate(weatherResult.getDt()));
                        wind_field.setText(new StringBuilder("풍속 : "+String.valueOf(weatherResult.getWind().getSpeed())).append("m/s").toString());
                        humidity_field.setText(new StringBuilder("습도 : "+String.valueOf(weatherResult.getMain().getHumidity())).append("%").toString());
                        //                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
//                            json.getJSONObject("sys").getLong("sunrise") * 1000,
//                            json.getJSONObject("sys").getLong("sunset") * 1000)));
//                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
//                                .append(weatherResult.getWeather().get(0).getIcon()).append(".png").toString()).into(img_weather);
                        setWeatherIcon(weatherResult.getId(),
                                Long.valueOf(Common.convertUnixToDate(weatherResult.getSys().getSunrise()))*1000,
                                Long.valueOf(Common.convertUnixToDate(weatherResult.getSys().getSunset()))*1000);
//                        panel.setVisibility(View.VISIBLE);
//                        loading.setVisibility(View.GONE);
                    }
                }, new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        Toast.makeText(getActivity(),""+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }));
    }


//        selectCity.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
//            alertDialog.setTitle("Change City");
//            final EditText input = new EditText(MainActivity.this);
//            input.setText(city);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT);
//            input.setLayoutParams(lp);
//            alertDialog.setView(input);
//
//            alertDialog.setPositiveButton("Change",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            city = input.getText().toString();
//                            taskLoadUp(city);
//                        }
//                    });
//            alertDialog.setNegativeButton("Cancel",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//            alertDialog.show();
//        }
//    });
//
//
//    public void taskLoadUp(String query) {
//        if (Function.isNetworkAvailable(ctx.getApplicationContext())) {
//            DownloadWeather task = new DownloadWeather();
//            task.execute(query);
//        } else {
//            Toast.makeText(ctx.getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    class DownloadWeather extends AsyncTask< String, Void, String > {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//        protected String doInBackground(String...args) {
//            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
//                    "&units=metric&lang=kr&appid=" + OPEN_WEATHER_MAP_API);
//            return xml;
//        }
//        @Override
//        protected void onPostExecute(String xml) {
//
//            try {
//                JSONObject json = new JSONObject(xml);
//                if (json != null) {
//                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
//                    JSONObject main = json.getJSONObject("main");
//                    DateFormat df = DateFormat.getDateTimeInstance();
//
//                    cityField.setText(json.getString("name").toUpperCase(Locale.KOREA) + ", " + json.getJSONObject("sys").getString("country"));
//                    detailsField.setText(details.getString("description").toUpperCase(Locale.KOREA));
//                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
//                    humidity_field.setText("습도: " + main.getString("humidity") + "%");
//                    updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
//                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
//                            json.getJSONObject("sys").getLong("sunrise") * 1000,
//                            json.getJSONObject("sys").getLong("sunset") * 1000)));
//
//                }
//            } catch (JSONException e) {
//                Toast.makeText(ctx.getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
//            }
//
//
//        }
        private void setWeatherIcon(int actualId, long sunrise, long sunset){
            int id = actualId / 100;
            String icon = "";
            if(actualId == 800){
                long currentTime = new Date().getTime();
                if(currentTime>=sunrise && currentTime<sunset) {
                    icon = ctx.getString(R.string.weather_sunny);
                } else {
                    icon = ctx.getString(R.string.weather_clear_night);
                }
            } else {
                switch(id) {
                    case 2 : icon = ctx.getString(R.string.weather_thunder);
                        break;
                    case 3 : icon = ctx.getString(R.string.weather_drizzle);
                        break;
                    case 7 : icon = ctx.getString(R.string.weather_foggy);
                        break;
                    case 8 : icon = ctx.getString(R.string.weather_cloudy);
                        break;
                    case 6 : icon = ctx.getString(R.string.weather_snowy);
                        break;
                    case 5 : icon = ctx.getString(R.string.weather_rainy);
                        break;
                }
            }
            weatherIcon.setText(icon);
        }
    }



