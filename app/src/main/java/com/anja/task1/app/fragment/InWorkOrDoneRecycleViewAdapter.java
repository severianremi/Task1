package com.anja.task1.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.anja.task1.app.DataModelApplication;
import com.anja.task1.app.R;
import com.anja.task1.app.Request;
import com.anja.task1.app.activity.MainActivity;
import com.anja.task1.app.activity.SelectedRequestActivity;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class InWorkOrDoneRecycleViewAdapter
        extends RecyclerView.Adapter<InWorkOrDoneRecycleViewAdapter.InWorkOrDoneViewHolder>
        implements View.OnClickListener{

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd MM yyyy");
    private List<Request> mRequests;
    private RecyclerView mRecyclerView;
    private DataModelApplication mDataModel;
    private Activity mActivity;

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setDataModel(DataModelApplication mDataModel) {
        this.mDataModel = mDataModel;
    }

    public void setRecycleView(RecyclerView mRecycleView) {
        this.mRecyclerView = mRecycleView;
    }

    public void setRequests(List<Request> mRequests) {
        this.mRequests = mRequests;
        notifyDataSetChanged();
    }

    @Override
    public InWorkOrDoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        view.setOnClickListener(this);
        return new InWorkOrDoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InWorkOrDoneViewHolder holder, int position) {
        Request request = mRequests.get(position);
        holder.requestIcon.setImageDrawable(holder.mRequestItem.getResources().getDrawable(request.getIcon()));
        holder.requestLikes.setText(String.valueOf(request.getLikes()));
        holder.requestTitle.setText(request.getTitle());
        holder.requestAddress.setText(request.getAddress());
        holder.requestCreateDate.setText(DATE_TIME_FORMATTER.print(request.getCreateDate()));
        holder.requestDays.setText(request.getDays());
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

    @Override
    public void onClick(View v) {
        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
        Request selectedRequest = mRequests.get(itemPosition);
        mDataModel.setSelectedRequest(selectedRequest);
        Intent intent = new Intent(v.getContext(), SelectedRequestActivity.class);
        mActivity.startActivity(intent);
    }

    public static class InWorkOrDoneViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.request_icon)
        ImageView requestIcon;
        @Bind(R.id.request_likes)
        TextView requestLikes;
        @Bind(R.id.request_title)
        TextView requestTitle;
        @Bind(R.id.request_address)
        TextView requestAddress;
        @Bind(R.id.request_create_date)
        TextView requestCreateDate;
        @Bind(R.id.request_days)
        TextView requestDays;

        private LinearLayout mRequestItem;

        public InWorkOrDoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRequestItem = (LinearLayout) itemView;
        }


    }

}
