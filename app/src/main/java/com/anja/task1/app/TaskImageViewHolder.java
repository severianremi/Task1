package com.anja.task1.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class TaskImageViewHolder extends RecyclerView.ViewHolder { //[Comment] it would be better to make this class internal (in adapter)

    ImageView imgView; //[Comment] Wrong name and visibility modifier

    public TaskImageViewHolder(View itemView) {
        super(itemView);
        this.imgView = (ImageView) itemView.findViewById(R.id.imgView); //[Comment] You don't need this here
    }
}