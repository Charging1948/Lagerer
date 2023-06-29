package model;

public class Order {
    private Product product;
    private int reward;

    public Order(Product product, int reward) {
        this.product = product;
        this.reward = reward;
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
}