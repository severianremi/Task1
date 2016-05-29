package com.anja.task1.app.view.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anja.task1.app.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderImageRecycleViewAdapter
        extends RecyclerView.Adapter<OrderImageRecycleViewAdapter.TaskImageViewHolder> {

    private static final String FILE_URL = "http://dev-contact.yalantis.com/files/ticket/";
    private List<String> mImageNames;
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

    public void setImageNames(List<String> images) {
        mImageNames = images;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final TaskImageViewHolder holder, final int position) {
//        Picasso
//                .with(holder.mTaskImageView.getContext())
//                .load(mImageNames.get(position))
//                .fit()
//                .centerInside()
//                .into(holder.mTaskImageView);

        Picasso.with(holder.mTaskImageView.getContext())
                .load(FILE_URL + mImageNames.get(position))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .centerInside()
                .into(holder.mTaskImageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(holder.mTaskImageView.getContext())
                                .load(FILE_URL + mImageNames.get(position))
                                .fit()
                                .centerInside()
                                .error(R.drawable.no_image_box)
                                .into(holder.mTaskImageView, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
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
