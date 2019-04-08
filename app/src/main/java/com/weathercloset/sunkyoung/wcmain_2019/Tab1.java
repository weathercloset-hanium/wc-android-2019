package com.weathercloset.sunkyoung.wcmain_2019;

/**
 * Created by SunKyoung on 2019-03-10.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static android.support.v7.content.res.AppCompatResources.getDrawable;


public class Tab1 extends Fragment {
    private Activity ctx;
    TextView selectCity, cityField, detailsField, currentTemperatureField, humidity_field, weatherIcon, updatedField;
//    ProgressBar loader;
    Typeface weatherFont;
    String city = "Seoul, KR";

    /* Please Put your API KEY here */
    private String OPEN_WEATHER_MAP_API = "c546ec57ad58273d9e4c1bbea6f1c672";

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

        // 날씨
        selectCity = (TextView) scrollView.findViewById(R.id.selectCity);
        cityField = (TextView) scrollView.findViewById(R.id.city_field);
        updatedField = (TextView) scrollView.findViewById(R.id.updated_field);
        detailsField = (TextView) scrollView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) scrollView.findViewById(R.id.current_temperature_field);
        humidity_field = (TextView) scrollView.findViewById(R.id.humidity_field);
        weatherIcon = (TextView) scrollView.findViewById(R.id.weather_icon);
        Typeface weatherFont = getResources().getFont(R.font.weathericons_regular_webfont);
        weatherIcon.setTypeface(weatherFont);

        taskLoadUp(city);
        return scrollView;
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
                    DateFormat df = DateFormat.getDateTimeInstance();

                    cityField.setText(json.getString("name").toUpperCase(Locale.KOREA) + ", " + json.getJSONObject("sys").getString("country"));
                    detailsField.setText(details.getString("description").toUpperCase(Locale.KOREA));
                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    humidity_field.setText("습도: " + main.getString("humidity") + "%");
                    updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));

                }
            } catch (JSONException e) {
                Toast.makeText(ctx.getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }

    }

}

