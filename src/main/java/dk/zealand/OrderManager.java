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

    public Order findOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public boolean markOrderAsReady(int id) {
        Order order = findOrderById(id);
        if (order == null) {
            return false;
        }
        if ("KLAR".equals(order.getStatus())) {
            return false;
        }
        order.setStatus("KLAR");
        return true;
    }

    public boolean cancelOrder(int id) {
        Order order = findOrderById(id);
        if (order == null) {
            return false;
        }
        if ("KLAR".equals(order.getStatus())) {
            return false;
        }
        order.setStatus("ANNULLERET");
        return true;
    }

    public int countPendingOrders() {
        int count = 0;
        for (Order order : orders) {
            if ("MODTAGET".equals(order.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public int getOrderCount() {
        return orders.size();
    }
}
