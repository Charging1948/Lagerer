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

    public Object toHTMLString() {
        if (this.fromSuccessfulOrder) {
            return "<html><body><h3>+" + this.amount + " from " + this.order.getProduct().toShortString() + "</h3></body></html>";
        } else {
            return "<html><body><h3>-" + this.amount + " from " + this.order.getProduct().toShortString() + "</h3></body></html>";
        }
    }
}
