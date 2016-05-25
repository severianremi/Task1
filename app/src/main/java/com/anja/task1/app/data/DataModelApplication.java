package com.anja.task1.app.data;

import android.app.Application;

import com.facebook.FacebookSdk;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 16.04.2016.
 */
public class DataModelApplication extends Application {

    private static List<Order> sInWorkOrders;
    private static List<Order> sDoneOrders;
    private static List<Order> sWaitOrders;
    private static Order sSelectedOrder;

    public static List<Order> getInWorkOrders() {
        return sInWorkOrders;
    }

    public static List<Order> getDoneOrders() {
        return sDoneOrders;
    }

    public static List<Order> getWaitOrders() {
        return sWaitOrders;
    }

    public static Order getSelectedOrder() {
        return sSelectedOrder;
    }

    public static void setSelectedOrder(Order selectedOrder) {
        sSelectedOrder = selectedOrder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        FacebookSdk.sdkInitialize(this);
        generateData();
    }

    private void generateData() {
        sInWorkOrders = new ArrayList<Order>();
        sDoneOrders = new ArrayList<Order>();
        sWaitOrders = new ArrayList<Order>();
        RandomOrderFactory factory = new RandomOrderFactory();

        for(int i = 0; i<10; i++){
            sInWorkOrders.add(factory.generateOrder(Order.Status.IN_WORK));
            sDoneOrders.add(factory.generateOrder(Order.Status.DONE));
            sWaitOrders.add(factory.generateOrder(Order.Status.WAIT));
        }
    }

}
