package com.anja.task1.app.service;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by Anna on 16.05.2016.
 */
public class TicketServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String url) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
        T service = retrofit.create(clazz);
        return service;
    }

}
