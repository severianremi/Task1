package com.anja.task1.app.view.impl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anja.task1.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderImageRecycleViewAdapter
        extends RecyclerView.Adapter<OrderImageRecycleViewAdapter.TaskImageViewHolder> {

    private List<String> mImages;
    private View.OnClickListener mOnClickListener;

    public OrderImageRecycleViewAdapter(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public TaskImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_request_image_item, parent, false);
        return new TaskImageViewHolder(view);
    }

    public void setImages(List<String> images) {
        mImages = images;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(TaskImageViewHolder holder, int position) {
        Picasso
                .with(holder.mTaskImageView.getContext())
                .load(mImages.get(position))
                .fit()
                .centerInside()
                .into(holder.mTaskImageView);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }


    public class TaskImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView mTaskImageView;

        public TaskImageViewHolder(View itemView) {
            super(itemView);
            mTaskImageView = (ImageView) itemView;
            mTaskImageView.setOnClickListener(mOnClickListener);
        }
    }

}
