package model;

import java.beans.PropertyChangeEvent;

public class OrderList extends BaseModel {
    private Order[] orderList;

    public OrderList() {
        super();
        this.orderList = new Order[3];
    }

    public void add(Order order) throws ArrayIndexOutOfBoundsException {
        // add order to the array, if there is space. If not, throw an exception that fits the situation
        for (int i = 0; i < this.orderList.length; i++) {
            if (this.orderList[i] == null) {
                this.orderList[i] = order;
                firePropertyChange("orderlist_add", null, order);
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException("No more space in the order list. Try completing an order first.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    /**
     * Get the order that contains the product
     * @param product The product to search for
     * @return The order that contains the product, or null if no order contains the product
     */
    public Order getOrderByProduct(Product product) {
        // get the order that contains the product
        for (Order order : this.orderList) {
            if (order.getProduct().equals(product)) {
                return order;
            }
        }
        return null;
    }

    public void remove(Order order) {
        // remove the order from the array
        for (int i = 0; i < this.orderList.length; i++) {
            if (this.orderList[i] == order) {
                this.orderList[i] = null;
                firePropertyChange("orderlist_remove", order, null);
                return;
            }
        }
    }
    
}
