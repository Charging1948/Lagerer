package model;

public class Order {
    private Product product;
    private double reward;

    public Order(Product product, double reward) {
        this.product = product;
        this.reward = reward;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getReward() {
        return this.reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}