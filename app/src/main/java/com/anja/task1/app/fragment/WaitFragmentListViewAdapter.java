package com.anja.task1.app.fragment;

import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.anja.task1.app.R;
import com.anja.task1.app.Request;
import com.anja.task1.app.util.DateConverter;

import java.util.List;

/**
 * Created by Anna on 26.04.2016.
 */
public class WaitFragmentListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private List<Request> mRequests;
    private OnRequestSelectListener mOnRequestSelectListener;



    public void setOnItemClickForListView(ListView listView) {
        listView.setOnItemClickListener(this);
    }

    public void setRequests(List<Request> mRequests) {
        this.mRequests = mRequests;
        notifyDataSetChanged();
    }

    public WaitFragmentListViewAdapter(OnRequestSelectListener onRequestSelectListener) {
        mOnRequestSelectListener = onRequestSelectListener;
    }

    @Override
    public int getCount() {
        return mRequests.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WaitViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.request_item, parent, false);
            viewHolder = new WaitViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (WaitViewHolder) convertView.getTag();
        }
        Request request = mRequests.get(position);

        viewHolder.mRequestIcon.setImageDrawable(ResourcesCompat
                .getDrawable(viewHolder.mRequestItem.getResources(), request.getIcon(), null));
        viewHolder.mRequestLikes.setText(String.valueOf(request.getLikes()));
        viewHolder.mRequestTitle.setText(request.getTitle());
        viewHolder.mRequestAddress.setText(request.getAddress());
        viewHolder.mRequestCreateDate.setText(DateConverter.toListItemFormat(request.getCreateDate()));
        viewHolder.mRequestDays.setText(request.getDays());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Request selectedRequest = mRequests.get(position);
        mOnRequestSelectListener.onRequestSelect(selectedRequest);
    }

    public static class WaitViewHolder {

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

        private RelativeLayout mRequestItem;

        public WaitViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
            mRequestItem = (RelativeLayout) itemView;
        }


    }
}
