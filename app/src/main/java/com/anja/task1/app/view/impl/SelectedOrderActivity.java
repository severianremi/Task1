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
import com.anja.task1.app.data.DataModelApplication;
import com.anja.task1.app.data.Order;
import com.anja.task1.app.util.DateConverter;
import com.anja.task1.app.view.SelectedOrderView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SelectedOrderActivity extends AppCompatActivity implements SelectedOrderView, View.OnClickListener {

    private OrderImageRecycleViewAdapter mImageAdapter;
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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setOnClickListenerForChildren(mMainSv);

        LinearLayoutManager lm
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mImagesRv.setLayoutManager(lm);
        mImageAdapter = new OrderImageRecycleViewAdapter();
        mImagesRv.setAdapter(mImageAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Order order = DataModelApplication.getSelectedOrder();
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
    public void onClick(View v) {
        String msg = v.getClass().getSimpleName() + " id: " + v.getResources().getResourceEntryName(v.getId());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setOnClickListenerForChildren(ViewGroup parent) {
        for (int i = 0; i <= parent.getChildCount() - 1; i++) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                setOnClickListenerForChildren((ViewGroup) child);
            } else {
                if (child != null && child instanceof TextView) {
                    child.setOnClickListener(this);
                }
            }
        }
    }

}
