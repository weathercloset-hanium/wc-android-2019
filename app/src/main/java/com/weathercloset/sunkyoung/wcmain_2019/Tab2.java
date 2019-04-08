package com.weathercloset.sunkyoung.wcmain_2019;

/**
 * Created by SunKyoung on 2019-03-10.
 */

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import static android.support.v7.content.res.AppCompatResources.getDrawable;

public class Tab2 extends Fragment {
    private Activity ctx;

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

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_tab2, container, false);
        ListView listview ;
        ListViewAdapter2 adapter;

        // Adapter 생성
        adapter = new ListViewAdapter2() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = linearLayout.findViewById(R.id.listview2);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(getDrawable(ctx, R.drawable.cardigan_col),
                getDrawable(ctx, R.drawable.jumper_col),getDrawable(ctx, R.drawable.coat_col),
                getDrawable(ctx, R.drawable.coat),getDrawable(ctx, R.drawable.cardigan),
                "0","0","0","0","0",
                "OUTER") ;
        // 두 번째 아이템 추가.
        adapter.addItem(getDrawable(ctx, R.drawable.shirt_col),
                getDrawable(ctx, R.drawable.tshirt_col),getDrawable(ctx, R.drawable.hoodie_col),
                getDrawable(ctx, R.drawable.hoodie),getDrawable(ctx, R.drawable.tshirt),
                "0","0","0","0","0",
                "TOP") ;
        // 세 번째 아이템 추가.
        adapter.addItem(getDrawable(ctx, R.drawable.dungarees_col),
                getDrawable(ctx, R.drawable.trousers_col),getDrawable(ctx, R.drawable.shorts_col),
                getDrawable(ctx, R.drawable.dungarees),getDrawable(ctx, R.drawable.trousers),
                "0","0","0","0","0",
                "BOTTOM") ;
        adapter.addItem(getDrawable(ctx, R.drawable.umbrella_col),
                getDrawable(ctx, R.drawable.umbrella),getDrawable(ctx, R.drawable.gasmask_col),
                getDrawable(ctx, R.drawable.gasmask),getDrawable(ctx, R.drawable.gasmask),
                "0","0","0","0","0",
                "ETC") ;
        // Inflate the layout for this fragment
        return linearLayout;
    }

}
