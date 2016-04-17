package com.anja.task1.app;

import android.app.Application;
import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 16.04.2016.
 */
public class DataModelApplication extends Application {

    private List<Request> mInWorkRequests;
    private List<Request> mDoneRequests;
    private List<Request> mWaitRequests;
    private Request mSelectedRequest;

    public List<Request> getInWorkRequests() {
        return mInWorkRequests;
    }

    public void setInWorkRequests(List<Request> mInWorkRequests) {
        this.mInWorkRequests = mInWorkRequests;
    }

    public List<Request> getDoneRequests() {
        return mDoneRequests;
    }

    public void setDoneRequests(List<Request> mDoneRequests) {
        this.mDoneRequests = mDoneRequests;
    }

    public List<Request> getWaitRequests() {
        return mWaitRequests;
    }

    public void setWaitRequests(List<Request> mWaitRequests) {
        this.mWaitRequests = mWaitRequests;
    }

    public Request getSelectedRequest() {
        return mSelectedRequest;
    }

    public void setSelectedRequest(Request mSelectedRequest) {
        this.mSelectedRequest = mSelectedRequest;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        generateData();
    }

    private void generateData() {
        mInWorkRequests = new ArrayList<Request>();
        mDoneRequests = new ArrayList<Request>();
        mWaitRequests = new ArrayList<Request>();
        RandomRequestFactory factory = new RandomRequestFactory();

        for(int i = 0; i<10; i++){
            mInWorkRequests.add(factory.generateRequest(Request.Status.IN_WORK));
            mDoneRequests.add(factory.generateRequest(Request.Status.DONE));
            mWaitRequests.add(factory.generateRequest(Request.Status.WAIT));
        }
    }
}
