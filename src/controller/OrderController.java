package controller;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderController extends BaseController {
    private List<Order> orderList;

    public OrderController() {
        this.orderList = new ArrayList<>();
    }


    public void createNewOrder(Order order) {
        this.orderList.add(order);
    }

    public Object getOrders() {
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
}