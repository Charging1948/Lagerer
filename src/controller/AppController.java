package controller;

import interfaces.OrderControllerDelegate;
import interfaces.WarehouseControllerDelegate;
import model.Order;
import model.Position;
import model.Product;

public class AppController implements WarehouseControllerDelegate, OrderControllerDelegate {

    private WarehouseController warehouseController;
    private OrderController orderController;
    private BalanceController balanceController;
    private StatusController statusController;

    public AppController() {
        warehouseController = new WarehouseController();
        this.warehouseController.setDelegate(this);

        orderController = new OrderController();
        this.orderController.setDelegate(this);

        balanceController = new BalanceController();
        statusController = new StatusController();
    }

    public WarehouseController getWarehouseController() {
        return this.warehouseController;
    }

    public OrderController getOrderController() {
        return this.orderController;
    }

    public BalanceController getBalanceController() {
        return this.balanceController;
    }

    public StatusController getStatusController() {
        return this.statusController;
    }



    @Override
    public void productRemoved(Product product, Position position) {
        Order order = orderController.getOrders().getOrderByProduct(product);
        if (orderController.hasActiveOrderForProduct(product)) {
            balanceController.updateBalance(product, true);
            orderController.markOrderAsFulfilled(product);
        } else {
            balanceController.updateBalance(product, false);
        }
    }

    @Override
    public void orderAdded(Order order) {
        statusController.setStatus("Order added");
    }

    @Override
    public void orderAborted(Order order) {
        
    }

    @Override
    public void orderFulfilled(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderFulfilled'");
    }
}