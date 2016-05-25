package com.anja.task1.app.view;

import android.view.View;

import com.anja.task1.app.data.Order;

/**
 * Created by Anna on 19.05.2016.
 */
public interface SelectedOrderView {

    void showMessage(String msg);
    void showOrder(Order order);
    void setOnClickListenerForChildren(View.OnClickListener onClickListener);


}
