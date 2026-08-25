package dk.zealand;

public class Order {
    private final int id;
    private final String dishName;
    private final int quantity;
    private final String status;

    public Order(int id, String dishName, int quantity, String status) {
        this.id = id;
        this.dishName = dishName;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDishName() {
        return dishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("Bestilling #%d: %s x%d - Status: %s", id, dishName, quantity, status);
    }
}
