package interfaces;

import model.Order;

public interface OrderPanelDelegate {
    void addGeneratedOrder();

    void trashSelectedOrder(Order selectedOrder);
}
