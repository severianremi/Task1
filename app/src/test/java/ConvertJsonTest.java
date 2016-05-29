/**
 * Created by Anna on 24.05.2016.
 */

    import com.anja.task1.app.data.Order;
    import com.anja.task1.app.data.TicketToOrderConverter;
    import com.anja.task1.app.data.ticket.Ticket;
    import com.anja.task1.app.data.service.TicketService;
    import com.anja.task1.app.data.service.TicketServiceFactory;

    import java.io.IOException;




    /**
     * Created by Anna on 12.05.2016.
     */
    public class ConvertJsonTest {

        public static void main(String[] args) throws IOException {
//            System.out.println(returnList());
//
        }
    }
//
//        private static List<String> returnList(){
//            List<String> images = new ArrayList<>();
//            String url;
//            String fileName = "7D7019C5-EE49-4719-8384-0FF98720E3F7.jpg, AC10B7F3-05DA-43FA-9748-1C8E1D5BBCED.jpg, 0E8BC31A-443A-4E2C-861F-59C280CBCA19.jpg, 4FBDA21C-8567-4745-930B-015FC9B527EC.jpg";
//            String[] fileNames = fileName.split("\\s+");
//            for (int i = 0; i < fileName.length()-1; i++) {
//                fileNames[i] = fileNames[i].replaceAll("[^\\w\\.\\w]", "");
//                System.out.println(fileNames[i]);
//                url = "http://dev-contact.yalantis.com/files/ticket/" + fileNames[i];
//                images.add(url);
//            }
//            return images;
//        }
//                TicketService service = TicketServiceFactory.createRetrofitService(TicketService.class, TicketService.BASE_URL);
//            service.getTickets("0,9,5,7,8,10,6,3,4,1")
//                    .subscribe(new Subscriber<Ticket[]>() {
//                        @Override
//                        public void onCompleted() {
//                            System.out.println("Complete");
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onNext(Ticket[] ticket) {
//                            TicketToOrderConverter ticketToOrderConverter = new TicketToOrderConverter();
//                            for (int i = 0; i<ticket.length; i++) {
//                                Ticket tick = ticket[i];
//
//                                Order order = ticketToOrderConverter.toOrder(tick);
//                                System.out.println("---------------------------------------------------");
//                                System.out.println(ticket[i]);
//                                System.out.println("----------------");
//                                if (order.getStatus() == Order.Status.IN_WORK) {
//                                    System.out.println(order.getImages());
//                                    System.out.println("----------------");
//                                } else if (order.getStatus() == Order.Status.WAIT){
//                                    System.out.println(order.getImages());
//                                    System.out.println("----------------");
//                                } else if (order.getStatus() == Order.Status.DONE){
//                                    System.out.println(order.getImages());
//                                    System.out.println("----------------");
//                                }
//
//                            }
//
//
//                            for (int i = 0; i<ticket.length; i++) {
//
//
//                                System.out.println("-----------------------------------------------------");
//                            }
//                        }
//                    });
//
//        }

//        public static void requestToJson(){
//            GsonBuilder builder = new GsonBuilder();
//            Gson gson = builder.setPrettyPrinting().create();
//            RandomOrderFactory factory = new RandomOrderFactory();
//
//            String json = gson.toJson(factory.generateOrder(Order.Status.IN_WORK));
//            System.out.println(json);
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(TicketService.BASE_URL)
//                    .build();

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

//
//            }


