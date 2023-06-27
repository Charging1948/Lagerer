package model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class OrderList extends BaseModel {
    private ArrayList<Order> orderList;

    public OrderList() {
        super();
        this.orderList = new ArrayList<Order>();
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
        firePropertyChange("", order, order);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    
}
