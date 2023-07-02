package controller;

import interfaces.OrderControllerDelegate;
import interfaces.StatusDelegate;
import interfaces.WarehouseControllerDelegate;
import model.Order;
import model.Position;
import model.Product;

public class AppController implements WarehouseControllerDelegate, OrderControllerDelegate, StatusDelegate {

    private WarehouseController warehouseController;
    private OrderController orderController;
    private BalanceController balanceController;
    private StatusController statusController;

    public AppController() {
        warehouseController = new WarehouseController();
        this.warehouseController.setDelegate(this);

        orderController = new OrderController();
        this.orderController.setDelegate(this);
        this.orderController.setStatusDelegate(this);

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
    public void setStatus(String status) {
        statusController.setStatus(status);
    }

    @Override
    public void productStored(Product product, Position position) {
        Order order = orderController.getOrderList().getOrderByProduct(product);
        if (order != null) {
            balanceController.updateBalance(order, true);
            orderController.markOrderAsFulfilled(order);
            warehouseController.resetWarehouseHighlighting();
        }
    }

    @Override
    public void productSentOut(Product product, Position position) {
        Order order = orderController.getOrderList().getOrderByProduct(product);
        if (order != null) {
            balanceController.updateBalance(order, true);
            orderController.markOrderAsFulfilled(order);
            warehouseController.resetWarehouseHighlighting();
        }
    }

    @Override
    public void productRemoved(Product product) {
        balanceController.trashPenalty(product);
        warehouseController.resetWarehouseHighlighting();
    }

    @Override
    public void orderAdded(Order order) {
        statusController.setStatus("Order added");
    }

    @Override
    public void orderAborted(Order order) {

        balanceController.trashPenalty(order);
        statusController.setStatus("Order aborted");
    }

    @Override
    public void orderFulfilled(Order order) {
        statusController.setStatus("Order fulfilled");
    }

    @Override
    public void orderSelected(Order order) {
        warehouseController.handleOrder(order);
        statusController.setStatus("Currently handling order for: " + order.getProduct().toString());
    }
}