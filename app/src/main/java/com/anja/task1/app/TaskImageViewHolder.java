package com.anja.task1.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class TaskImageViewHolder extends RecyclerView.ViewHolder {

    ImageView imgView;

    public TaskImageViewHolder(View itemView) {
        super(itemView);
        this.imgView = (ImageView) itemView.findViewById(R.id.imgView);
    }
}