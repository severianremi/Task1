package com.anja.task1.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.squareup.picasso.Picasso;

public class InWorkAndDoneRecycleViewAdapter extends RecyclerView.Adapter<InWorkAndDoneRecycleViewAdapter.InWorkAndDoneViewHolder> {

    @Override
    public InWorkAndDoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new InWorkAndDoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InWorkAndDoneViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public static class InWorkAndDoneViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mRequestItem;

        public InWorkAndDoneViewHolder(View itemView) {
            super(itemView);
            mRequestItem = (LinearLayout) itemView;
        }
    }

}
