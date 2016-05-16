package com.anja.task1.app;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 16.04.2016.
 */
public class DataModelApplication extends Application {

    private static List<Request> sInWorkRequests;
    private static List<Request> sDoneRequests;
    private static List<Request> sWaitRequests;
    private static Request sSelectedRequest;

    public static List<Request> getInWorkRequests() {
        return sInWorkRequests;
    }

    public static List<Request> getDoneRequests() {
        return sDoneRequests;
    }

    public static List<Request> getWaitRequests() {
        return sWaitRequests;
    }

    public static Request getSelectedRequest() {
        return sSelectedRequest;
    }

    public static void setSelectedRequest(Request selectedRequest) {
        sSelectedRequest = selectedRequest;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        generateData();
    }

    private void generateData() {
        sInWorkRequests = new ArrayList<Request>();
        sDoneRequests = new ArrayList<Request>();
        sWaitRequests = new ArrayList<Request>();
        RandomRequestFactory factory = new RandomRequestFactory();

        for(int i = 0; i<10; i++){
            sInWorkRequests.add(factory.generateRequest(Request.Status.IN_WORK));
            sDoneRequests.add(factory.generateRequest(Request.Status.DONE));
            sWaitRequests.add(factory.generateRequest(Request.Status.WAIT));
        }
    }
}
