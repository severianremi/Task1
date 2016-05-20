package com.anja.task1.app.presenter;

import android.support.v7.widget.RecyclerView;

import com.anja.task1.app.data.DataModelApplication;
import com.anja.task1.app.view.TicketListView;

/**
 * Created by Anna on 19.05.2016.
 */
public class FragmentsPresenterImpl implements FragmentPresenter{


    TicketListView mTicketListView;

    public FragmentsPresenterImpl(TicketListView mTicketListView) {
        this.mTicketListView = mTicketListView;
    }

    @Override
    public RecyclerView.OnScrollListener buttonStateChanged() {
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    fab.show();
//                } else {
//                    fab.hide();
//                }
            }
        };
        return onScrollListener;
    }

    @Override
    public void obtainData(String TYPE_KEY) {
//        int type = getArguments().getInt(TYPE_KEY);
//        if (type == IN_WORK){
//            mAdapter.setRequests(DataModelApplication.getInWorkRequests());
//        }else if (type == DONE){
//            mAdapter.setRequests(DataModelApplication.getDoneRequests());
//        }else if (type == WAIT){
//            mAdapter.setRequests(DataModelApplication.getWaitRequests());
//        }
    }
}
