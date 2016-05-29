package com.anja.task1.app.presenter;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.anja.task1.app.data.Order;
import com.anja.task1.app.data.OrderRepository;
import com.anja.task1.app.view.OrderListView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anna on 19.05.2016.
 */
public class OrderListPresenterImpl extends OrderListPresenter {

    private static final int ORDER_COUNT_ON_PAGE = 20;
    private boolean mLoading = true; // True if we are still waiting for the last set of data to load.
    private int mVisibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    private int mPreviousTotal = 0; // The total number of items in the dataset after the last load
    private int mCurrentPage = 1;
    private Order.Status mDataType;
    private OrderListView mOrderListView;
    private OrderRepository mOrderRepository;

    public void setOrderRepository(OrderRepository orderRepository) {
        this.mOrderRepository = orderRepository;
    }

    public OrderListPresenterImpl(OrderListView mOrderListView, Order.Status dataType) {
        this.mOrderListView = mOrderListView;
        mDataType = dataType;
    }

    @Override
    public void onResume() {
        new OrderLoader().execute();
    }

    private List<Order> getOrders() {
        try {
            return mOrderRepository.getOrders(mDataType, ORDER_COUNT_ON_PAGE, calcOrderCountByPages());
        } catch (Exception e) {
            e.printStackTrace();
            mOrderListView.showMessage(e.toString());
        }
        return Collections.EMPTY_LIST;
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
        mOrderRepository.setSelectedOrder(selectedOrder);
        mOrderListView.startSelectedOrderActivity();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mOrderListView.getItemCount();
        int firstVisibleItem = mOrderListView.findFirstVisibleItemPosition();

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = totalItemCount;
            }
        }
        if (!mLoading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + mVisibleThreshold)) {
            synchronized (this) {
                mCurrentPage++;
            }
            new OrderLoader().execute();
            mLoading = true;
        }
    }

    private int calcOrderCountByPages(){
        return (mCurrentPage-1) * ORDER_COUNT_ON_PAGE;
    }

    private class OrderLoader extends AsyncTask<String, Void, List<Order>> {

        @Override
        protected List<Order> doInBackground(String... params) {
              return getOrders();
        }

        @Override
        protected void onPostExecute(List<Order> result) {
            synchronized (OrderListPresenterImpl.this){
                mCurrentPage = getCurrentPageByOrderCount(result.size());
            }
            mOrderListView.showOrders(result);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private int getCurrentPageByOrderCount(int count){
        if (count == 0){
            return 1;
        }
        int page = count/ORDER_COUNT_ON_PAGE;
        if(count % ORDER_COUNT_ON_PAGE > 0){
            page++;
        }return page;
    }
}
