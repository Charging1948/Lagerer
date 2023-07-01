package model;

public class Order {
    private Product product;
    private int reward;
    private OrderType type;

    public enum OrderType {
        INBOUND, OUTBOUND
    }

    public Order(Product product, int reward, OrderType type) {
        this.product = product;
        this.reward = reward;
        this.type = type;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getReward() {
        return this.reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public OrderType getType() {
        return this.type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Order: %s %n %s %n %s %n", this.product, this.reward, this.type);
    }

    public String toHTMLString() {
        return String.format("<html><body><h2>Order</h2><br>%s<br>%s €<br>%s</body></html>", this.product, this.reward, this.type);
    }
}