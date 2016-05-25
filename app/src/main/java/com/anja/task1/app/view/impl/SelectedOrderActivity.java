package com.anja.task1.app.view.impl;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anja.task1.app.R;
import com.anja.task1.app.data.Order;
import com.anja.task1.app.presenter.SelectedOrderPresenter;
import com.anja.task1.app.presenter.SelectedOrderPresenterImpl;
import com.anja.task1.app.util.DateConverter;
import com.anja.task1.app.view.SelectedOrderView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SelectedOrderActivity extends AppCompatActivity implements SelectedOrderView {

    private OrderImageRecycleViewAdapter mImageAdapter;
    private SelectedOrderPresenter mSelectedOrderPresenter;

    @Bind(R.id.selected_order_main_sv)
    ViewGroup mMainSv;
    @Bind(R.id.selected_order_images_rv)
    RecyclerView mImagesRv;
    @Bind(R.id.selected_order_title)
    TextView mOrderTitle;
    @Bind(R.id.selected_order_status)
    TextView mOrderStatus;
    @Bind(R.id.selected_order_create_date)
    TextView mOrderCreateDate;
    @Bind(R.id.selected_order_register_date)
    TextView mOrderRegisterDate;
    @Bind(R.id.selected_order_deadline_date)
    TextView mOrderDeadlineDate;
    @Bind(R.id.selected_order_responsible)
    TextView mOrderResponsible;
    @Bind(R.id.selected_order_text)
    TextView mOrderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_order);
        ButterKnife.bind(this);
        mSelectedOrderPresenter = new SelectedOrderPresenterImpl(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        LinearLayoutManager lm
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mImagesRv.setLayoutManager(lm);
        mImageAdapter = new OrderImageRecycleViewAdapter(mSelectedOrderPresenter);
        mImagesRv.setAdapter(mImageAdapter);
        mSelectedOrderPresenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSelectedOrderPresenter.onResume();
    }

    @Override
    public void showOrder(Order order) {
        mOrderTitle.setText(order.getTitle());
        String status = getResources().getString(order.getStatus().getTitleId());
        mOrderStatus.setText(status);
        mOrderCreateDate.setText(DateConverter.toSelectedOrderFormat(order.getCreateDate()));
        mOrderRegisterDate.setText(DateConverter.toSelectedOrderFormat(order.getRegisterDate()));
        mOrderDeadlineDate.setText(DateConverter.toSelectedOrderFormat(order.getDeadlineDate()));
        mOrderResponsible.setText(order.getResponsible());
        mOrderText.setText(order.getText());
        mImageAdapter.setImages(order.getImages());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOnClickListenerForChildren(View.OnClickListener onClickListener) {
        setOnClickListenerForChildren(mMainSv, onClickListener);
    }

    private void setOnClickListenerForChildren(ViewGroup parent, View.OnClickListener onClickListener) {
        for (int i = 0; i <= parent.getChildCount() - 1; i++) {
            View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                setOnClickListenerForChildren((ViewGroup) child, onClickListener);
            } else {
                if (child != null && child instanceof TextView) {
                    child.setOnClickListener(onClickListener);
                }
            }
        }
    }
}
