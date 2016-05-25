package com.anja.task1.app.presenter;

import android.support.v7.widget.RecyclerView;

import com.anja.task1.app.OnOrderSelectListener;

/**
 * Created by Anna on 20.05.2016.
 */
public abstract class OrderListPresenter extends RecyclerView.OnScrollListener implements OnOrderSelectListener {

    public abstract void onResume();

}
