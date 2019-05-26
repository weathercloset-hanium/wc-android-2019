package com.weathercloset.sunkyoung.wcmain_2019;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by SunKyoung on 2019-03-24.
 */

public class ListViewAdapter2 extends BaseAdapter{
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem2> listViewItemList2 = new ArrayList<ListViewItem2>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter2() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList2.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item2, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView1 = convertView.findViewById(R.id.imageView1) ;
        ImageView iconImageView2 = convertView.findViewById(R.id.imageView2) ;
        ImageView iconImageView3 = convertView.findViewById(R.id.imageView3) ;
        ImageView iconImageView4 = convertView.findViewById(R.id.imageView4) ;
        ImageView iconImageView5 = convertView.findViewById(R.id.imageView5) ;
        final TextView countTextView1 = convertView.findViewById(R.id.countText1) ;
        final TextView countTextView2 = convertView.findViewById(R.id.countText2) ;
        final TextView countTextView3 = convertView.findViewById(R.id.countText3) ;
        final TextView countTextView4 = convertView.findViewById(R.id.countText4) ;
        final TextView countTextView5 = convertView.findViewById(R.id.countText5) ;
        final TextView titleTextView = convertView.findViewById(R.id.textView1) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewItem2 listViewItem2 = listViewItemList2.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView1.setImageDrawable(listViewItem2.getIcon1());
        iconImageView2.setImageDrawable(listViewItem2.getIcon2());
        iconImageView3.setImageDrawable(listViewItem2.getIcon3());
        iconImageView4.setImageDrawable(listViewItem2.getIcon4());
        iconImageView5.setImageDrawable(listViewItem2.getIcon5());
        countTextView1.setText(listViewItem2.getCount1());
        countTextView2.setText(listViewItem2.getCount2());
        countTextView3.setText(listViewItem2.getCount3());
        countTextView4.setText(listViewItem2.getCount4());
        countTextView5.setText(listViewItem2.getCount5());
        titleTextView.setText(listViewItem2.getTitle());

        // 클릭 이벤트
        iconImageView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
                countTextView1.setText(String.valueOf(Integer.parseInt(listViewItem2.getCount1())+1));
            }
        });
        iconImageView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
                countTextView2.setText(String.valueOf(Integer.parseInt(listViewItem2.getCount2())+1));
            }
        });
        iconImageView3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
                countTextView3.setText(String.valueOf(Integer.parseInt(listViewItem2.getCount3())+1));
            }
        });
        iconImageView4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
                countTextView4.setText(String.valueOf(Integer.parseInt(listViewItem2.getCount4())+1));
            }
        });
        iconImageView5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
                countTextView5.setText(String.valueOf(Integer.parseInt(listViewItem2.getCount5())+1));
            }
        });


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList2.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon1,Drawable icon2,Drawable icon3,Drawable icon4,Drawable icon5,
                        String count1, String count2, String count3, String count4, String count5, String title) {
        ListViewItem2 item = new ListViewItem2();

        item.setIcon1(icon1);
        item.setIcon2(icon2);
        item.setIcon3(icon3);
        item.setIcon4(icon4);
        item.setIcon5(icon5);
        item.setTitle(title);
        item.setCount1(count1);
        item.setCount2(count2);
        item.setCount3(count3);
        item.setCount4(count4);
        item.setCount5(count5);

        listViewItemList2.add(item);
    }
}
