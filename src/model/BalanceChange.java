package model;

public class BalanceChange {
    private int amount;
    private Order order;
    private boolean fromSuccessfulOrder;

    public BalanceChange(Order order, boolean fromSuccessfulOrder) {
        this.order = order;
        this.amount = order.getReward();
        this.fromSuccessfulOrder = fromSuccessfulOrder;
    }

    public int getAmount() {
        return this.amount;
    }

    public Order getOrder() {
        return this.order;
    }

    public boolean isFromSuccessfulOrder() {
        return this.fromSuccessfulOrder;
    }
}
