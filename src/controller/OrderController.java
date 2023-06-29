package controller;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import interfaces.OrderControllerDelegate;
import model.Order;
import model.OrderList;
import model.Product;
import view.OrderPanel;

public class OrderController implements IController {
    private OrderList orderList;
    private OrderPanel orderPanel;
    private OrderControllerDelegate delegate;

    public void setDelegate(OrderControllerDelegate delegate) {
        this.delegate = delegate;
    }

    public OrderController() {
        this.orderList = new OrderList();
        this.orderPanel = new OrderPanel();

        this.orderList.addPropertyChangeListener(this);
    }

    public void createNewOrder(Order order) {
        this.orderList.add(order);
    }

    public OrderList getOrders() {
        return this.orderList;
    }

    public OrderPanel getOrderPanel() {
        return this.orderPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    public boolean hasActiveOrderForProduct(Product product) {
        return false;
    }

    public void markOrderAsFulfilled(Product product) {
    }
}