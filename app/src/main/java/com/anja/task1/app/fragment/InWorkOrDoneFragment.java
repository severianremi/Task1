package com.anja.task1.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.anja.task1.app.DataModelApplication;
import com.anja.task1.app.R;
import com.anja.task1.app.activity.MainActivity;
import com.software.shell.fab.ActionButton;


/**
 * Created by Anna on 11.04.2016.
 */
public class InWorkOrDoneFragment extends Fragment {

    public static final String TYPE_KEY = "type";
    public static final int IN_WORK = 0;
    public static final int DONE = 1;

    private DataModelApplication mDataModel;
    private InWorkOrDoneRecycleViewAdapter mAdapter = new InWorkOrDoneRecycleViewAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.in_work_and_done_fragment, null);
        RecyclerView recyclerViewItems = (RecyclerView) view.findViewById(R.id.in_work_or_done_rv);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewItems.setLayoutManager(linearLayoutManager);

        final ActionButton fab = ((MainActivity) getActivity()).getFab();
        recyclerViewItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show();
                } else {
                    fab.hide();
                }
            }
        });
        mDataModel = (DataModelApplication) getActivity().getApplication();
        obtainDate();
        recyclerViewItems.setAdapter(mAdapter);
        mAdapter.setRecycleView(recyclerViewItems);
        mAdapter.setDataModel(mDataModel);
        mAdapter.setActivity(getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        obtainDate();
    }

    private void obtainDate() {
        int type = getArguments().getInt(TYPE_KEY);
        if (type == IN_WORK){
            mAdapter.setRequests(mDataModel.getInWorkRequests());
        }else if (type == DONE){
            mAdapter.setRequests(mDataModel.getDoneRequests());
        }
    }
}
