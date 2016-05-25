package com.anja.task1.app.data;

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

    //    TODO private int mIcon;
    private Random mRandom = new Random();

    public Order ticketToOrderConverter(Order.Status status, Ticket ticket) {
        Order order = new Order();
        //TODO Get from ticket
        order.setStatus(status);
        order.setTitle(ticket.getTitle());
        order.setCreateDate(new DateTime(ticket.getCreate_date()));
        order.setRegisterDate(new DateTime(ticket.getStart_date()));
        order.setDeadlineDate(new DateTime(ticket.getDeadline()));
        order.setResponsible(getResponsible(ticket.getPerformers()));
        order.setText(ticket.getBody());
        order.setImages(getListImagesUrl(ticket.getFiles()));
        order.setLikes(ticket.getLikes_counter());
        order.setAddress(getFullAddress(ticket.getUser().getAddress()));
        //TODO consider how count this Days
        order.setDays((mRandom.nextInt(10) + 2) + " днів");
        return order;
    }

    private List<String> getListImagesUrl(List<ImageFiles> imageFiles) {
        List<String> imagesUrl = new ArrayList<>();
        for (int i = 0; i < imageFiles.size(); i++) {
            String filename = imageFiles.get(i).getFilename();
            filename = "http://dev-contact.yalantis.com/files/ticket/" + filename;
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

}
