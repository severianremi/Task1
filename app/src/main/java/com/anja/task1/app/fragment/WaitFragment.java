package com.anja.task1.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import com.anja.task1.app.DataModelApplication;
import com.anja.task1.app.R;
import com.anja.task1.app.Request;
import com.anja.task1.app.activity.MainActivity;
import com.anja.task1.app.activity.SelectedRequestActivity;
import com.software.shell.fab.ActionButton;

/**
 * Created by Anna on 13.04.2016.
 */
public class WaitFragment extends Fragment implements AbsListView.OnScrollListener, OnRequestSelectListener{

    private ActionButton mFab;
    private WaitFragmentListViewAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAdapter = new WaitFragmentListViewAdapter(this);
        View view = inflater.inflate(R.layout.wait_fragment, null);
        ListView listView = (ListView) view.findViewById(R.id.wait_fragment_lv);
        mAdapter.setRequests(DataModelApplication.getWaitRequests());
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
        mAdapter.setRequests(DataModelApplication.getWaitRequests());
    }

    @Override
    public void onRequestSelect(Request selectedRequest) {
        DataModelApplication.setSelectedRequest(selectedRequest);
        Intent intent = new Intent(getContext(), SelectedRequestActivity.class);
        getActivity().startActivity(intent);
    }
}
