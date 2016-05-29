package com.anja.task1.app.data;

import android.util.Log;

import com.anja.task1.app.data.service.TicketService;
import com.anja.task1.app.data.ticket.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by Anna on 29.05.2016.
 */
public class OrderRepositoryImpl implements OrderRepository {

    private static Map<Order.Status, String> sOrderRequestByStatus;

    static {
        sOrderRequestByStatus = new HashMap<>();
        sOrderRequestByStatus.put(Order.Status.IN_WORK, "0,9,5,7,8");
        sOrderRequestByStatus.put(Order.Status.DONE, "10,6");
        sOrderRequestByStatus.put(Order.Status.WAIT, "3,4,1");
    }

    private TicketService mTicketService;
    private Order mSelectedOrder;
    private OrderDao mOrderDao;
    private TicketToOrderConverter mTicketToOrderConverter = new TicketToOrderConverter();


    public void setTicketService(TicketService ticketService) {
        this.mTicketService = ticketService;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.mOrderDao = orderDao;
    }

    @Override
    public void setSelectedOrder(Order selectedOrder) {
        this.mSelectedOrder = selectedOrder;
    }

    @Override
    public Order getSelectedOrder() {
        return mSelectedOrder;
    }

    @Override
    public List<Order> getOrders(Order.Status status, int amount, int offset) {
        List<Order> orders = mOrderDao.loadOrders(status);
        int minCount = amount + offset;
        if (orders.size() >= minCount) {
            return orders;
        }
        List<Order> fromServiceOrders = loadFromService(status, amount, offset);
        mOrderDao.saveOrders(fromServiceOrders);
        orders.addAll(fromServiceOrders);
        return orders;
    }

    private List<Order> loadFromService(Order.Status status, int amount, int offset) {
        Response<Ticket[]> response = null;
        try {
            response = mTicketService
                    .getTickets(sOrderRequestByStatus.get(status), amount, offset).execute();

            Ticket[] tickets = response.body();
            List<Order> orders = new ArrayList<>();
            for (Ticket ticket : tickets) {
                orders.add(mTicketToOrderConverter.toOrder(ticket));
            }
            return orders;
        } catch (IOException e) {
            Log.e("OrderRepositoryImpl", e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public void clearCache() {
        mOrderDao.deleteOrders();
    }
}
