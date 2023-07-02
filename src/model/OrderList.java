package model;

import javax.swing.DefaultListModel;

import model.helpers.DummyOrderGenerator;

public class OrderList extends BaseModel {
    private DefaultListModel<Order> orderListModel;
    private DummyOrderGenerator dummyOrderGenerator;

    public OrderList() {
        super();
        this.orderListModel = new DefaultListModel<>();
        this.dummyOrderGenerator = new DummyOrderGenerator();
    }

    public void add(Order order) throws ArrayIndexOutOfBoundsException {
        // add order to the array, if there is space. If not, throw an exception that
        // fits the situation
        if (this.orderListModel.getSize() >= 3)
            throw new ArrayIndexOutOfBoundsException("No more space in the order list. Try completing an order first.");
        this.orderListModel.addElement(order);
        firePropertyChange("orderlist_add", null, order);
        return;
    }

    /**
     * Get the order that contains the product
     * 
     * @param product The product to search for
     * @return The order that contains the product, or null if no order contains the
     *         product
     */
    public Order getOrderByProduct(Product product) {
        // get the order of the orderListModel that contains the product
        for (int i = 0; i < this.orderListModel.getSize(); i++) {
            Order order = this.orderListModel.get(i);
            if (order.getProduct().equals(product))
                return order;
        }
        
        return null;
    }

    public void remove(Order order) {
        // remove the order from the orderListModel
        this.orderListModel.removeElement(order);
    }

    public Order addGeneratedOrder() {
        Order order = this.dummyOrderGenerator.getNextOrder();
        this.add(order);
        return order;
    }

    public DefaultListModel<Order> getOrderDefaultListModel() {
        return this.orderListModel;
    }

}
