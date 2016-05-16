package com.anja.task1.app.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anna on 12.05.2016.
 */
public interface TicketService  {

    String BASE_URL = "http://dev-contact.yalantis.com/rest/v1/";

    @GET("tickets")
    Call<ResponseBody> getTickets(@Query("state") String user);
}
