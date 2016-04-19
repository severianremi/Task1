package com.anja.task1.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.anja.task1.app.DataModelApplication;
import com.anja.task1.app.R;
import com.anja.task1.app.Request;
import com.anja.task1.app.activity.MainActivity;
import com.anja.task1.app.activity.SelectedRequestActivity;
import com.anja.task1.app.util.DateConverter;
import com.software.shell.fab.ActionButton;

import java.util.List;

/**
 * Created by Anna on 13.04.2016.
 */
public class WaitFragment extends Fragment implements AbsListView.OnScrollListener{

    private ActionButton mFab;
    private DataModelApplication mDataModel;
    private WaitFragmentListViewAdapter mAdapter = new WaitFragmentListViewAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wait_fragment, null);
        ListView listView = (ListView) view.findViewById(R.id.wait_fragment_lv);
        mDataModel = (DataModelApplication) getActivity().getApplication();
        mAdapter.setRequests(mDataModel.getWaitRequests());
        mAdapter.setActivity(getActivity());
        mAdapter.setOnItemClickForListView(listView);
        listView.setAdapter(mAdapter);

        mFab = ((MainActivity) getActivity()).getFab();
        listView.setOnScrollListener(this);

        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState == SCROLL_STATE_IDLE){
            mFab.show();
        } else {
            mFab.hide();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setRequests(mDataModel.getWaitRequests());
    }



    private class WaitFragmentListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        private Activity mActivity;
        private List<Request> mRequests;

        public void setOnItemClickForListView(ListView listView) {
            listView.setOnItemClickListener(this);
        }

        public void setActivity(Activity mActivity) {
            this.mActivity = mActivity;
        }

        public void setRequests(List<Request> mRequests) {
            this.mRequests = mRequests;
            notifyDataSetChanged();
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
            Request request = mRequests.get(position);
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
            ((ImageView) v.findViewById(R.id.request_icon)).setImageDrawable(getResources()
                    .getDrawable(request.getIcon()));
            ((TextView) v.findViewById(R.id.request_likes)).setText(String.valueOf(request.getLikes()));
            ((TextView) v.findViewById(R.id.request_title)).setText(request.getTitle());
            ((TextView) v.findViewById(R.id.request_address)).setText(request.getAddress());
            ((TextView) v.findViewById(R.id.request_create_date))
                    .setText(DateConverter.toListItemFormat(request.getCreateDate()));
            ((TextView) v.findViewById(R.id.request_days)).setText(request.getDays());
            return v;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Request selectedRequest = mRequests.get(position);
            mDataModel.setSelectedRequest(selectedRequest);
            Intent intent = new Intent(view.getContext(), SelectedRequestActivity.class);
            mActivity.startActivity(intent);
        }
    }
}
