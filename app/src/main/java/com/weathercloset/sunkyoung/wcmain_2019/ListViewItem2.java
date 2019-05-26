package com.weathercloset.sunkyoung.wcmain_2019;

import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

/**
 * Created by SunKyoung on 2019-03-24.
 */

public class ListViewItem2 {
    private Drawable icon1 ;
    private Drawable icon2 ;
    private Drawable icon3 ;
    private Drawable icon4 ;
    private Drawable icon5 ;
    private String titleStr ;
    private String count1 ;
    private String count2 ;
    private String count3 ;
    private String count4 ;
    private String count5 ;

    public void setIcon1(Drawable icon) {
        icon1 = icon ;
    }
    public void setIcon2(Drawable icon) {
        icon2 = icon ;
    }
    public void setIcon3(Drawable icon) {
        icon3 = icon ;
    }
    public void setIcon4(Drawable icon) {
        icon4 = icon ;
    }
    public void setIcon5(Drawable icon) {
        icon5 = icon ;
    }
    public void setTitle(String title) { titleStr = title ; }
    public void setCount1(String count) { count1 = count; }
    public void setCount2(String count) { count2 = count; }
    public void setCount3(String count) { count3 = count; }
    public void setCount4(String count) { count4 = count; }
    public void setCount5(String count) { count5 = count; }

    public Drawable getIcon1() {
        return this.icon1 ;
    }
    public Drawable getIcon2() {
        return this.icon2 ;
    }
    public Drawable getIcon3() {
        return this.icon3 ;
    }
    public Drawable getIcon4() {
        return this.icon4 ;
    }
    public Drawable getIcon5() {
        return this.icon5 ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getCount1() { return this.count1 ; }
    public String getCount2() { return this.count2 ; }
    public String getCount3() { return this.count3 ; }
    public String getCount4() { return this.count4 ; }
    public String getCount5() { return this.count5 ; }


}

