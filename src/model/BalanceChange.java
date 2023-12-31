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

    public BalanceChange() {
        this.amount = 300;
        this.fromSuccessfulOrder = false;
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

    private String getVerb() {
        String result = "";

        if (this.isFromSuccessfulOrder()) {
            result = switch (this.order.getType()) {
                case INBOUND -> "storing ";
                case OUTBOUND -> "sending out ";
            };
        } else {
            result = "trashing ";
        }

        return result;
    }

    public Object toHTMLString() {
        if (this.fromSuccessfulOrder) {
            return "<html><body><h3>+" + this.amount + " from " + this.getVerb()
                    + this.order.getProduct().toShortString() + "</h3></body></html>";
        } else {
            return "<html><body><h3>-" + this.amount + " from " + this.getVerb()
                    + this.order.getProduct().toShortString() + "</h3></body></html>";
        }
    }
}
