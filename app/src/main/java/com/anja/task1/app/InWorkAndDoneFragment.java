package com.anja.task1.app;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Anna on 11.04.2016.
 */
public class InWorkAndDoneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.in_work_and_done_fragment, null);
        RecyclerView recyclerViewItems = (RecyclerView) view.findViewById(R.id.in_work_and_done_rv);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewItems.setLayoutManager(linearLayoutManager);
        InWorkAndDoneRecycleViewAdapter adapter = new InWorkAndDoneRecycleViewAdapter();
        recyclerViewItems.setAdapter(adapter);
        return view;

    }

}
