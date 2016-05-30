package com.anja.task1.app.view.impl;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anja.task1.app.OnOrderSelectListener;
import com.anja.task1.app.R;
import com.anja.task1.app.data.Order;
import com.anja.task1.app.util.DateConverter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderListRecycleViewAdapter
        extends RecyclerView.Adapter<OrderListRecycleViewAdapter.TicketListViewHolder> {


    private List<Order> mOrders;

    private OnOrderSelectListener mOnOrderSelectListener;

    public void setOnRequestSelectListener(OnOrderSelectListener onOrderSelectListener) {
        mOnOrderSelectListener = onOrderSelectListener;
    }

    public void setOrders(List<Order> mOrders) {
        this.mOrders = mOrders;
        notifyDataSetChanged();
    }

    @Override
    public TicketListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new TicketListViewHolder(view, mOrders, mOnOrderSelectListener);
    }

    @Override
    public void onBindViewHolder(TicketListViewHolder holder, int position) {
        Order order = mOrders.get(position);
        holder.mRequestIcon.setImageDrawable(ResourcesCompat
                .getDrawable(holder.mRequestItem.getResources(), order.getIcon(), null));
        holder.mRequestLikes.setText(String.valueOf(order.getLikes()));
        holder.mRequestTitle.setText(order.getTitle());
        holder.mRequestAddress.setText(order.getAddress());
        holder.mRequestCreateDate.setText(DateConverter.toListItemFormat(order.getCreateDate()));
        holder.mRequestDays.setText(order.getDays());
    }

    @Override
    public int getItemCount() {
        if(mOrders == null){
            return 0;
        }
        return mOrders.size();
    }


    public static class TicketListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.request_icon)
        ImageView mRequestIcon;
        @Bind(R.id.request_likes)
        TextView mRequestLikes;
        @Bind(R.id.request_title)
        TextView mRequestTitle;
        @Bind(R.id.request_address)
        TextView mRequestAddress;
        @Bind(R.id.request_create_date)
        TextView mRequestCreateDate;
        @Bind(R.id.request_days)
        TextView mRequestDays;

        private List<Order> mOrders;
        private OnOrderSelectListener mOnOrderSelectListener;

        private RelativeLayout mRequestItem;

        public TicketListViewHolder(View itemView, List<Order> orders,
                                    OnOrderSelectListener onOrderSelectListener) {
            super(itemView);
            mOrders = orders;
            mOnOrderSelectListener = onOrderSelectListener;
            ButterKnife.bind(this, itemView);
            mRequestItem = (RelativeLayout) itemView;
            mRequestItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if(itemPosition >= mOrders.size()){
                return;
            }
            Order selectedOrder = mOrders.get(itemPosition);
            mOnOrderSelectListener.onOrderSelect(selectedOrder);
        }


    }

}
