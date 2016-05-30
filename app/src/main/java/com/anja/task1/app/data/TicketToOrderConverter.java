package com.anja.task1.app.data;

import com.anja.task1.app.R;
import com.anja.task1.app.data.ticket.Address;
import com.anja.task1.app.data.ticket.ImageFiles;
import com.anja.task1.app.data.ticket.Performer;
import com.anja.task1.app.data.ticket.Ticket;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Anna on 24.05.2016.
 */
public class TicketToOrderConverter {

    private int mIcon = R.drawable.ic_doc;
    private Random mRandom = new Random();

    public Order toOrder(Ticket ticket) {
        Order order = new Order();
        order.setId(ticket.getId());
        order.setStatus(definitionStatus(ticket.getState().getId()));
        order.setTitle(ticket.getTitle());
        order.setCreateDate(new DateTime(ticket.getCreate_date()*1000));
        order.setRegisterDate(new DateTime(ticket.getStart_date()*1000));
        order.setDeadlineDate(new DateTime(ticket.getDeadline()*1000));
        order.setResponsible(getResponsible(ticket.getPerformers()));
        order.setText(ticket.getBody());
        order.setImages(getListImagesUrl(ticket.getFiles()));
        order.setLikes(ticket.getLikes_counter());
        order.setAddress(getFullAddress(ticket.getUser().getAddress()));
        order.setDays((mRandom.nextInt(10) + 2) + " днів");
        order.setIcon(mIcon);
        return order;
    }

    private List<String> getListImagesUrl(List<ImageFiles> imageFiles) {
        List<String> imagesUrl = new ArrayList<>();
        for (int i = 0; i < imageFiles.size(); i++) {
            String filename = imageFiles.get(i).getFilename();
            imagesUrl.add(filename);
        }
        return imagesUrl;
    }

    private String getFullAddress(Address address) {
        String city = address.getCity().getName();
        String street = address.getStreet().getName();
        String streetType = address.getStreet().getStreet_type().getShort_name();
        String house = address.getHouse().getName();
        String flat = address.getFlat();
        return city + ", " + streetType + " " + street + ", " + house + ", " + flat;
    }

    private String getResponsible(List<Performer> performers) {
        if (performers == null || performers.isEmpty()) {
            return null;
        }
        return performers.get(0).getOrganization();
    }

    private Order.Status definitionStatus(int status){
        if(status == 0 || status == 9 || status == 5 || status == 7 || status == 8){
            return Order.Status.IN_WORK;
        } else if (status == 10 || status == 6){
            return Order.Status.DONE;
        }else if (status == 1 || status == 3|| status == 4){
            return Order.Status.WAIT;
        }
        return null;
    }

}
