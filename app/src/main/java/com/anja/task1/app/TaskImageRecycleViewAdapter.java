package com.anja.task1.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class TaskImageRecycleViewAdapter extends RecyclerView.Adapter<TaskImageViewHolder> {

    private List<Integer> images = Arrays.asList(
            R.drawable.manhole,
            R.drawable.open_manhole,
            R.drawable.trams,
            R.drawable.donetsk_street,
            R.drawable.luke,
            R.drawable.open_luke);

    @Override
    public TaskImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_image_item, parent, false);
        return new TaskImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskImageViewHolder holder, int position) {
        Picasso
                .with(holder.imgView.getContext())
                .load(images.get(position))
                .fit()
                .centerInside()
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}