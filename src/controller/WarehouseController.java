package controller;

import java.beans.PropertyChangeEvent;

import exceptions.StorageException;
import model.Product;
import model.Warehouse;
import view.WarehousePanel;

public class WarehouseController implements IController {
    private Warehouse warehouse;
    private WarehousePanel warehousePanel;

    public WarehouseController() {
        this.warehouse = new Warehouse();
        this.warehouse.addPropertyChangeListener(this);
        this.warehousePanel = new WarehousePanel(Warehouse.getHeight(), Warehouse.getWidth()); // Initialize view with the model
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public WarehousePanel getWarehousePanel() {
        return this.warehousePanel;
    }

    public void storeProduct(Product product, int column, int row) throws StorageException {
        this.warehouse.storeProduct(product, column, row);
    }

    public Product getProduct(int column, int row) {
        return this.warehouse.getProduct(column, row);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.warehousePanel.updatePanel(this.warehouse);
    }

}