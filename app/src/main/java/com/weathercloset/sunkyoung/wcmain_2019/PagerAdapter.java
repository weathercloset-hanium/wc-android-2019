package com.weathercloset.sunkyoung.wcmain_2019;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by SunKyoung on 2019-03-10.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
//    private final List<Fragment> fragmentList = new ArrayList<>();
//    private final List<String> fragmentTitle = new ArrayList<>();
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return  tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

//    public void addFragment(Fragment fragment, String title){
//        fragmentList.add(fragment);
//        fragmentTitle.add(title);
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position){
//        return fragmentTitle.get(position);
//    }
}

