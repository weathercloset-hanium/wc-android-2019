package com.weathercloset.sunkyoung.wcmain_2019;

import android.graphics.drawable.Drawable;

/**
 * Created by SunKyoung on 2019-02-28.
 */

public class ListViewItem {
    private Drawable icon1 ;
    private Drawable icon2 ;
    private Drawable icon3 ;
    private Drawable icon4 ;
    private Drawable icon5 ;
    private String titleStr ;

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
    public void setTitle(String title) {
        titleStr = title ;
    }

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
}
