package com.weathercloset.sunkyoung.wcmain_2019;

/**
 * Created by SunKyoung on 2019-02-28.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView1 = convertView.findViewById(R.id.imageView1) ;
        ImageView iconImageView2 = convertView.findViewById(R.id.imageView2) ;
        ImageView iconImageView3 = convertView.findViewById(R.id.imageView3) ;
        ImageView iconImageView4 = convertView.findViewById(R.id.imageView4) ;
        ImageView iconImageView5 = convertView.findViewById(R.id.imageView5) ;
        TextView titleTextView = convertView.findViewById(R.id.textView1) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView1.setImageDrawable(listViewItem.getIcon1());
        iconImageView2.setImageDrawable(listViewItem.getIcon2());
        iconImageView3.setImageDrawable(listViewItem.getIcon3());
        iconImageView4.setImageDrawable(listViewItem.getIcon4());
        iconImageView5.setImageDrawable(listViewItem.getIcon5());
        titleTextView.setText(listViewItem.getTitle());

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
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon1,Drawable icon2,Drawable icon3,Drawable icon4,Drawable icon5, String title) {
        ListViewItem item = new ListViewItem();

        item.setIcon1(icon1);
        item.setIcon2(icon2);
        item.setIcon3(icon3);
        item.setIcon4(icon4);
        item.setIcon5(icon5);
        item.setTitle(title);

        listViewItemList.add(item);
    }
}

