package com.deneme.caulis.caulis.Calendar.Tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.deneme.caulis.caulis.Calendar.CalendarTab;
import com.deneme.caulis.caulis.Calendar.Event.EventTab;

public class ViewTabAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int numbOfTabs;

    public ViewTabAdapter(FragmentManager fm, CharSequence titles[], int mNumbOfTabs) {
        super(fm);
        this.titles = titles;
        this.numbOfTabs = mNumbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            CalendarTab compactCalendarTab = new CalendarTab();
            return compactCalendarTab;
        } else {
            EventTab tab2 = new EventTab();
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}