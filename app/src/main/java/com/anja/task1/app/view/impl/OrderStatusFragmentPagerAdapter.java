package com.anja.task1.app.view.impl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Anna on 26.04.2016.
 */
public class OrderStatusFragmentPagerAdapter extends FragmentStatePagerAdapter {

    List<OrderPageItem> mItems;

    public OrderStatusFragmentPagerAdapter(FragmentManager fm, List<OrderPageItem> items) {
        super(fm);
        mItems = items;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).getPageTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return  mItems.get(position).getPage();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}

