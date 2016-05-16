import com.anja.task1.app.RandomRequestFactory;
import com.anja.task1.app.Request;
import com.anja.task1.app.dto.Ticket;
import com.anja.task1.app.service.TicketService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Anna on 12.05.2016.
 */
public class ConvertJsonTest {

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TicketService.BASE_URL)
                .build();

        TicketService ticketService = retrofit.create(TicketService.class);
        Call<ResponseBody> call = ticketService.getTickets("0,9,5,7,8 ");
        Response<ResponseBody> response = call.execute();
        ResponseBody body = response.body();

        String json = body.string();

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        List<Ticket> tickets = gson.fromJson(json, new TypeToken<ArrayList<Ticket>>() {}.getType());
        Ticket ticket = tickets.get(2);
        System.out.println(ticket);

    }

    public static void requestToJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        RandomRequestFactory factory = new RandomRequestFactory();

        String json = gson.toJson(factory.generateRequest(Request.Status.IN_WORK));
        System.out.println(json);
    }

}
