package com.anja.task1.app.fragment;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.anja.task1.app.R;
import com.anja.task1.app.Request;
import com.anja.task1.app.util.DateConverter;

import java.util.List;

public class InWorkOrDoneRecycleViewAdapter
        extends RecyclerView.Adapter<InWorkOrDoneRecycleViewAdapter.InWorkOrDoneViewHolder> {

    private List<Request> mRequests;

    private  OnRequestSelectListener mOnRequestSelectListener;

    public void setOnRequestSelectListener(OnRequestSelectListener onRequestSelectListener) {
        mOnRequestSelectListener = onRequestSelectListener;
    }

    public void setRequests(List<Request> mRequests) {
        this.mRequests = mRequests;
        notifyDataSetChanged();
    }

    @Override
    public InWorkOrDoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new InWorkOrDoneViewHolder(view, mRequests, mOnRequestSelectListener);
    }

    @Override
    public void onBindViewHolder(InWorkOrDoneViewHolder holder, int position) {
        Request request = mRequests.get(position);
        holder.mRequestIcon.setImageDrawable(ResourcesCompat
                .getDrawable(holder.mRequestItem.getResources(), request.getIcon(), null));
        holder.mRequestLikes.setText(String.valueOf(request.getLikes()));
        holder.mRequestTitle.setText(request.getTitle());
        holder.mRequestAddress.setText(request.getAddress());
        holder.mRequestCreateDate.setText(DateConverter.toListItemFormat(request.getCreateDate()));
        holder.mRequestDays.setText(request.getDays());
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }



    public static class InWorkOrDoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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

        private List<Request> mRequests;
        private  OnRequestSelectListener mOnRequestSelectListener;

        private RelativeLayout mRequestItem;

        public InWorkOrDoneViewHolder(View itemView, List<Request> requests,
                                      OnRequestSelectListener onRequestSelectListener) {
            super(itemView);
            mRequests = requests;
            mOnRequestSelectListener = onRequestSelectListener;
            ButterKnife.bind(this, itemView);
            mRequestItem = (RelativeLayout) itemView;
            mRequestItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Request selectedRequest = mRequests.get(itemPosition);
            mOnRequestSelectListener.onRequestSelect(selectedRequest);
        }


    }

}
