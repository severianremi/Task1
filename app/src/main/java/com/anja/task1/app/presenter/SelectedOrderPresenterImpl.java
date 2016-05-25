package com.anja.task1.app.presenter;

import android.view.View;

import com.anja.task1.app.data.DataModelApplication;
import com.anja.task1.app.view.SelectedOrderView;

/**
 * Created by Anna on 24.05.2016.
 */
public class SelectedOrderPresenterImpl implements SelectedOrderPresenter{

    private SelectedOrderView mSelectedOrderView;

    public SelectedOrderPresenterImpl(SelectedOrderView selectedOrderView) {
        this.mSelectedOrderView = selectedOrderView;
    }

    @Override
    public void onClick(View v) {
        String msg = v.getClass().getSimpleName() + " id: "
                + v.getResources().getResourceEntryName(v.getId());
        mSelectedOrderView.showMessage(msg);
    }

    @Override
    public void onResume() {
        mSelectedOrderView.showOrder(DataModelApplication.getSelectedOrder());
    }

    @Override
    public void onCreate() {
        mSelectedOrderView.setOnClickListenerForChildren(this);
    }
}
