package controller;

import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;

import javax.swing.JOptionPane;

import exceptions.StorageException;
import model.Position;
import model.Product;
import model.Warehouse;
import view.WarehousePanel;
import interfaces.WarehouseControllerDelegate;
import interfaces.WarehousePanelDelegate;

public class WarehouseController implements IController, WarehousePanelDelegate {
    private Warehouse warehouse;
    private WarehousePanel warehousePanel;
    private WarehouseControllerDelegate delegate;

    public void setDelegate(WarehouseControllerDelegate delegate) {
        this.delegate = delegate;
    }

    public WarehouseController() {
        this.warehouse = new Warehouse();
        this.warehouse.addPropertyChangeListener(this);
        this.warehousePanel = new WarehousePanel(Warehouse.getHeight(), Warehouse.getWidth()); // Initialize view with

        this.warehousePanel.setDelegate(this);
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public WarehousePanel getWarehousePanel() {
        return this.warehousePanel;
    }

    public void storeProduct(Product product, Position position) throws StorageException {
        this.warehouse.storeProduct(product, position);
    }

    public Product getProduct(Position position) {
        return this.warehouse.getProduct(position);
    }

    public void removeProduct(Position position) {
        this.warehouse.removeProduct(position);
    }

    public void setDelegate(WarehousePanelDelegate delegate) {
        this.warehousePanel.setDelegate(delegate);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // get the product that was stored
        Product product = (Product) evt.getNewValue();
        // get the position where the product was stored
        Position position = Warehouse.getPositionFromPropertyChangeName(evt.getPropertyName());

        this.warehousePanel.updateGridSpot(product, position);
    }

    @Override
    public void confirmRemoveProduct(Product product, Position position) {
        try {
            int dialogResult = JOptionPane.showConfirmDialog(this.warehousePanel,
                    "Would You Like to Remove the Product?", "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.removeProduct(position);
            }
        } catch (HeadlessException e) {
            System.out.println("Not in a GUI environment. Cannot show dialog.");
        }
    }

}