package com.anja.task1.app.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Anna on 26.04.2016.
 */
public class RequestStatusFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static final int IN_WORK_TAB_POSITION = 0;
    private static final int DONE_TAB_POSITION = 1;
    private static final int WAIT_TAB_POSITION = 2;
    private static final int TAB_COUNT = 3;

    private String[] mTabNames;

    public RequestStatusFragmentPagerAdapter(FragmentManager fm, String[] tabNames) {
        super(fm);
        mTabNames = tabNames;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabNames[position];
    }

    @Override
    public Fragment getItem(int position) {
         switch (position) {
            case IN_WORK_TAB_POSITION: {
                return createFragment(TicketListFragment.IN_WORK);
            }
            case DONE_TAB_POSITION: {
                return createFragment(TicketListFragment.DONE);
            }
            case WAIT_TAB_POSITION:
                return createFragment(TicketListFragment.WAIT);
            default:
                return null;
        }
    }

    private TicketListFragment createFragment(int type){
        TicketListFragment fragment = new TicketListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TicketListFragment.TYPE_KEY, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}

