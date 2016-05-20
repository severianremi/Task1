package com.anja.task1.app.presenter;

import android.support.v7.widget.RecyclerView;

import com.software.shell.fab.ActionButton;

/**
 * Created by Anna on 20.05.2016.
 */
public interface FragmentPresenter {

    RecyclerView.OnScrollListener buttonStateChanged();
    void obtainData(String TYPE_KEY);
}
