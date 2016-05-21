package com.anja.task1.app.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anja.task1.app.OnOrderSelectListener;
import com.anja.task1.app.R;
import com.anja.task1.app.data.DataModelApplication;
import com.anja.task1.app.data.Order;
import com.anja.task1.app.presenter.OrderListPresenter;
import com.anja.task1.app.view.OrderListView;
import com.software.shell.fab.ActionButton;

import java.util.List;


/**
 * Created by Anna on 11.04.2016.
 */
public class OrderListFragment extends Fragment implements OrderListView, OnOrderSelectListener {

    private OrderListRecycleViewAdapter mAdapter = new OrderListRecycleViewAdapter();
    private OrderListPresenter mOrderListPresenter;

    public void setmOrderListPresenter(OrderListPresenter orderListPresenter) {
        this.mOrderListPresenter = orderListPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.in_work_and_done_fragment, null);
        RecyclerView recyclerViewItems = (RecyclerView) view.findViewById(R.id.in_work_or_done_rv);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewItems.setLayoutManager(linearLayoutManager);

        final ActionButton fab = ((MainActivity) getActivity()).getFab();
        recyclerViewItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                } else {
                    fab.hide();
                }
            }
        });

        recyclerViewItems.setAdapter(mAdapter);
        mAdapter.setOnRequestSelectListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mOrderListPresenter.onResume();
    }

    public void showOrders(List<Order> orders) {
        mAdapter.setOrders(orders);
    }

    @Override
    public void onOrderSelect(Order selectedOrder) {
        DataModelApplication.setSelectedOrder(selectedOrder);
        Intent intent = new Intent(getContext(), SelectedOrderActivity.class);
        getActivity().startActivity(intent);
    }
}
