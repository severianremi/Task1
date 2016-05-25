package com.anja.task1.app.presenter;

import android.support.v7.widget.RecyclerView;

import com.anja.task1.app.data.DataModelApplication;
import com.anja.task1.app.data.Order;
import com.anja.task1.app.view.OrderListView;

import java.util.List;

/**
 * Created by Anna on 19.05.2016.
 */
public class OrderListPresenterImpl extends OrderListPresenter {


    private Order.Status mDataType;
    private OrderListView mOrderListView;

    public OrderListPresenterImpl(OrderListView mOrderListView, Order.Status dataType) {
        this.mOrderListView = mOrderListView;
        mDataType = dataType;
    }

    @Override
    public void onResume() {
        mOrderListView.showOrders(getOrders());
    }

    public List<Order> getOrders() {
        switch (mDataType) {
            case IN_WORK:
                return DataModelApplication.getInWorkOrders();
            case DONE:
                return DataModelApplication.getDoneOrders();
            case WAIT:
                return DataModelApplication.getWaitOrders();
            default:
                return null;
        }
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            mOrderListView.showButton();
        } else {
            mOrderListView.hideButton();
        }
    }

    @Override
    public void onOrderSelect(Order selectedOrder) {
        DataModelApplication.setSelectedOrder(selectedOrder);
        mOrderListView.startSelectedOrderActivity();
    }
}
