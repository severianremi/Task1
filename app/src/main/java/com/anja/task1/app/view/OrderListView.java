package com.anja.task1.app.view;

import com.anja.task1.app.data.Order;

import java.util.List;

/**
 * Created by Anna on 19.05.2016.
 */
public interface OrderListView {

    void showOrders(List<Order> orders);
    void startSelectedOrderActivity();
    void showButton();
    void hideButton();

    void showMessage(String message);

    int getItemCount();

    int findFirstVisibleItemPosition();
}
