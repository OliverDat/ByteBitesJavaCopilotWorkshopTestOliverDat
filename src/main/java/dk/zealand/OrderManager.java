package dk.zealand;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final int MAX_ORDERS = 10;
    private final List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public Order createOrder(String dishName, int quantity) {
        if (orders.size() >= MAX_ORDERS) {
            return null;
        }
        Order order = new Order(nextOrderId++, dishName, quantity, "MODTAGET");
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public int getOrderCount() {
        return orders.size();
    }
}
