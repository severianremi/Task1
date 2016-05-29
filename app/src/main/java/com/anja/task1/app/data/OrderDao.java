package com.anja.task1.app.data;

import java.util.List;

/**
 * Created by Anna on 29.05.2016.
 */
public interface OrderDao {

    void saveOrders(List<Order> orders);
    List<Order> loadOrders(Order.Status status);

    void deleteOrders();
}
