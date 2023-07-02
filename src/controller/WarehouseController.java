package controller;

import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import exceptions.StorageException;
import model.*;
import view.WarehousePanel;
import interfaces.*;

public class WarehouseController implements PropertyChangeListener, WarehousePanelDelegate {
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

    public void sendOutProduct(Position position) {
        this.warehouse.sendOutProduct(position);
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

        if(this.delegate != null) {
            switch(Warehouse.getMethodFromPropertyChangeName(evt.getPropertyName())) {
                case STORE -> this.delegate.productStored(product, position);
                case SENDOUT -> this.delegate.productSentOut((Product) evt.getOldValue(), position);
                case REMOVE -> this.delegate.productRemoved((Product) evt.getOldValue());
            }
        } else {
            System.out.println("Delegate is null");
        }
    }

    @Override
    public void placeProduct(Product product, Position position) {
        try {
            this.storeProduct(product, position);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }

    
    @Override
    public void shipOutProduct(Product product, Position position) {
        this.removeProduct(position);
    }

    @Override
    public void confirmRemoveProduct(Position position) {
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

    public void handleOrder(Order order) {
        // Use lambda switch expression to handle order depending on order type
        switch (order.getType()) {
            case INBOUND:
                this.warehousePanel.highlightEmptyLocations(order.getProduct());
                break;
            case OUTBOUND:
                this.warehousePanel.highlightMatchingProductLocations(order.getProduct());
                break;
        }
    }

    public void resetWarehouseHighlighting() {
        this.warehousePanel.removeHighlights();
    }
}