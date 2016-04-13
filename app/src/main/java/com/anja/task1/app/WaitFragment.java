package com.anja.task1.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Anna on 13.04.2016.
 */
public class WaitFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wait_fragment, null);
        ListView listView = (ListView) view.findViewById(R.id.wait_fragment_lv);
        listView.setAdapter(new WaitFragmentListViewAdapter());


        return view;
    }
    private class WaitFragmentListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 10;
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
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        }
    }
}
