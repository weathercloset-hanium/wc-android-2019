package com.weathercloset.sunkyoung.wcmain_2019;

/**
 * Created by SunKyoung on 2019-03-10.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.support.v7.content.res.AppCompatResources.getDrawable;


public class Tab1 extends Fragment {
    private Activity ctx;
    TextView  cityField, detailsField, currentTemperatureField, humidity_field, wind_field, weatherIcon, updatedField;
    ImageButton BtnLocation;
//    ProgressBar loader;
    Typeface weatherFont;
    String city = "Seoul, KR";
    ListView listforecastview;

    /* Please Put your API KEY here */
    private String OPEN_WEATHER_MAP_API = "";

    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            ctx = (Activity) context;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
//
        ScrollView scrollView = (ScrollView) inflater.inflate(R.layout.fragment_tab1, container, false);
        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = scrollView.findViewById(R.id.listview1);
        listforecastview = scrollView.findViewById(R.id.listforecastview);
        listview.setAdapter(adapter);

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
        adapter.addItem(getDrawable(ctx, R.drawable.umbrella_col),
                getDrawable(ctx, R.drawable.umbrella),getDrawable(ctx, R.drawable.gasmask_col),
                getDrawable(ctx, R.drawable.gasmask),getDrawable(ctx, R.drawable.gasmask),
                "ETC ITEM") ;

        // 날씨
        cityField = (TextView) scrollView.findViewById(R.id.city_field);
        updatedField = (TextView) scrollView.findViewById(R.id.updated_field);
        detailsField = (TextView) scrollView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) scrollView.findViewById(R.id.current_temperature_field);
        wind_field = (TextView) scrollView.findViewById(R.id.wind_field);
        humidity_field = (TextView) scrollView.findViewById(R.id.humidity_field);
        weatherIcon = (TextView) scrollView.findViewById(R.id.weather_icon);
        Typeface weatherFont = getResources().getFont(R.font.weathericons_regular_webfont);
        weatherIcon.setTypeface(weatherFont);
        BtnLocation = scrollView.findViewById(R.id.btn_location);



        BtnLocation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
                alertDialog.setTitle("Change City");
                final EditText input = new EditText(ctx);
                input.setText(city);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Change",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                city = input.getText().toString();
                                taskLoadUp(city);
                            }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        });

        taskLoadUp(city);
        taskforecastLoadUp(city);
        return scrollView;
    }

    // current weather
    public void taskLoadUp(String query) {
        if (Function.isNetworkAvailable(ctx.getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(ctx.getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    class DownloadWeather extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&lang=kr&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    JSONObject wind = json.getJSONObject("wind");
                    DateFormat df = DateFormat.getDateTimeInstance();

                    cityField.setText(json.getString("name").toUpperCase(Locale.KOREA) + ", " + json.getJSONObject("sys").getString("country"));
                    detailsField.setText(details.getString("description").toUpperCase(Locale.KOREA));
                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    wind_field.setText("풍속: "+wind.getString("speed")+"m/s");
                    humidity_field.setText("습도: " + main.getString("humidity") + "%");
                    updatedField.setText(Function.convertUnixToDate(json.getLong("dt")));
                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                }
            } catch (JSONException e) {
                Toast.makeText(ctx.getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }

    }
    // forecast weather
    public void taskforecastLoadUp(String query) {
        if (Function.isNetworkAvailable(ctx.getApplicationContext())) {
            DownloadWeatherForecast task = new DownloadWeatherForecast();
            task.execute(query);
        } else {
            Toast.makeText(ctx.getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    class DownloadWeatherForecast extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/forecast?q=" + args[0] +
                    "&units=metric&lang=kr&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONArray details = json.getJSONArray("list");

                    ListViewForecastAdapter listViewForecastAdapter;
                    listViewForecastAdapter = new ListViewForecastAdapter();
                    listforecastview.setAdapter(listViewForecastAdapter);
                    String dt_diff = new SimpleDateFormat("yyyy년 MM월 dd일 EEE").format(new Date());
                    Log.d("today",dt_diff);
                    for(int i=0; i<details.length(); i++){
                        JSONObject jsonObject = details.getJSONObject(i);
                        if(dt_diff.equals(Function.convertUnixToDate(jsonObject.getLong("dt")))) continue;
                        listViewForecastAdapter.addItem(
                                Function.convertUnixToDay(jsonObject.getLong("dt")),
                                Html.fromHtml(Function.setForecastWeatherIcon(jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id"))),
                                String.format("%d",jsonObject.getJSONObject("main").getInt("temp_min"))+"/"+String.format("%d",jsonObject.getJSONObject("main").getInt("temp_max"))
                            );
                        dt_diff = Function.convertUnixToDate(jsonObject.getLong("dt"));
                    }

                }
            } catch (JSONException e) {
                Toast.makeText(ctx.getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }

    }

}

