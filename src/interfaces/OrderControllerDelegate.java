package interfaces;

import model.Order;

public interface OrderControllerDelegate {
    void orderAdded(Order order);
    void orderAborted(Order order);
    void orderFulfilled(Order order);
}
