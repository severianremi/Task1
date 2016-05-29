package com.anja.task1.app.data;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.anja.task1.app.data.service.TicketService;
import com.anja.task1.app.data.service.TicketServiceFactory;
import com.facebook.FacebookSdk;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Anna on 16.04.2016.
 */
public class Global extends Application {

    private static OrderRepository sOrderRepository;
    private static FBProfileDao sFbProfileDao;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        FacebookSdk.sdkInitialize(this);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

        DbHelper dbHelper = new DbHelper(this);

        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.setDbHelper(dbHelper);
        orderRepository.setOrderDao(orderDao);
        orderRepository.setTicketService(
                TicketServiceFactory.createRetrofitService(TicketService.class,
                        TicketService.BASE_URL));
        sOrderRepository = orderRepository;

        FBProfileDaoImpl fbProfileDao = new FBProfileDaoImpl();
        fbProfileDao.setDbHelper(dbHelper);
        sFbProfileDao = fbProfileDao;
    }

    public static OrderRepository getOrderRepository(){
        return sOrderRepository;
    }

    public static FBProfileDao getFBProfileDao() {
        return sFbProfileDao;
    }
}


