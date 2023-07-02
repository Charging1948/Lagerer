package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interfaces.OrderControllerDelegate;
import interfaces.OrderPanelDelegate;
import interfaces.StatusDelegate;
import model.Order;
import model.OrderList;
import view.OrderPanel;

public class OrderController implements PropertyChangeListener, OrderPanelDelegate {
    private OrderList orderList;
    private OrderPanel orderPanel;
    private OrderControllerDelegate delegate;
    private StatusDelegate statusDelegate;

    public void setDelegate(OrderControllerDelegate delegate) {
        this.delegate = delegate;
    }

    public void setStatusDelegate(StatusDelegate statusDelegate) {
        this.statusDelegate = statusDelegate;
    }

    public OrderController() {
        this.orderList = new OrderList();
        this.orderPanel = new OrderPanel(this.orderList.getOrderDefaultListModel());

        this.orderList.addPropertyChangeListener(this);
        this.orderPanel.addPropertyChangeListener(this);
        this.orderPanel.setDelegate(this);
    }

    @Override
    public void addGeneratedOrder() {
        try {
            this.orderList.addGeneratedOrder();
        } catch (ArrayIndexOutOfBoundsException e) {
            this.statusDelegate.setStatus("Cannot add more orders. Try fulfilling some first.");
        }
    }

    public void createNewOrder(Order order) {
        this.orderList.add(order);
    }

    public OrderList getOrderList() {
        return this.orderList;
    }

    public OrderPanel getOrderPanel() {
        return this.orderPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("order_selected")) {
            Order order = (Order) evt.getNewValue();
            if (order != null) {
                this.delegate.orderSelected(order);
            }
        }
    }

    public void markOrderAsFulfilled(Order order) {
        this.orderList.remove(order);
        this.statusDelegate.setStatus("Order fulfilled");
        System.out.println("Order fulfilled");
    }

    @Override
    public void trashSelectedOrder(Order selectedOrder) {
        this.orderList.remove(selectedOrder);
        this.statusDelegate.setStatus("Order trashed");
        this.delegate.orderAborted(selectedOrder);
        System.out.println("Order trashed");
    }
}