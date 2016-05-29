package com.anja.task1.app.data;

import java.util.List;

/**
 * Created by Anna on 29.05.2016.
 */
public interface OrderRepository {

    void setSelectedOrder(Order selectedOrder);
    Order getSelectedOrder();
    List<Order> getOrders(Order.Status status, int amount, int offset);

    void clearCache();
}
