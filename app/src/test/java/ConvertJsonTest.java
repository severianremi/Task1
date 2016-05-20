import com.anja.task1.app.data.RandomRequestFactory;
import com.anja.task1.app.data.Request;
import com.anja.task1.app.data.ticket.Ticket;
import com.anja.task1.app.data.service.TicketService;
import com.anja.task1.app.data.service.TicketServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;


import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * Created by Anna on 12.05.2016.
 */
public class ConvertJsonTest {

    public static void main(String[] args) throws IOException {
        TicketService service = TicketServiceFactory.createRetrofitService(TicketService.class, TicketService.BASE_URL);
        service.getTickets("0,9,5,7,8")
                 .subscribe(new Subscriber<Ticket[]>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Ticket[] ticket) {
                        System.out.println(Arrays.toString(ticket));
                    }
                });
//
    }

    public static void requestToJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        RandomRequestFactory factory = new RandomRequestFactory();

        String json = gson.toJson(factory.generateRequest(Request.Status.IN_WORK));
        System.out.println(json);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TicketService.BASE_URL)
                .build();

//        TicketService ticketService = retrofit.create(TicketService.class);
//        Call<ResponseBody> call = ticketService.getTickets("0,9,5,7,8 ");
//        Response<ResponseBody> response = call.execute();
//        ResponseBody body = response.body();
//
//        String json = body.string();
//
//        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
//        List<Ticket> tickets = gson.fromJson(json, new TypeToken<ArrayList<Ticket>>() {}.getType());
//        Ticket ticket = tickets.get(2);
    }

}
