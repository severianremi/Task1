package com.anja.task1.app.view.impl;

import android.support.v4.app.Fragment;

/**
 * Created by Anna on 21.05.2016.
 */
public class OrderPageItem {

    private String mPageTitle;
    private Fragment mPage;

    public OrderPageItem(String pageTitle, Fragment page) {
        this.mPageTitle = pageTitle;
        this.mPage = page;
    }

    public String getPageTitle() {
        return mPageTitle;
    }

    public Fragment getPage() {
        return mPage;
    }
}
