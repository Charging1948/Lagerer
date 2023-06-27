package model;

import java.beans.PropertyChangeEvent;

import exceptions.InvalidProductPlacementException;
import exceptions.WarehouseFullException;

public class Warehouse extends BaseModel{
    private Product[][] storage;
    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;

    public Warehouse() {
        storage = new Product[WIDTH][HEIGHT];
    }

    public void storeProduct(Product product, int x, int y) throws WarehouseFullException, InvalidProductPlacementException {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("Invalid storage position");
        }
        if (storage[x][y] != null) {
            throw new WarehouseFullException("This position is already occupied");
        }

        //auslagern als abstrakte Methode in Product
        if (!product.isValidPlacement(x, y)) {
            throw new InvalidProductPlacementException("Invalid product placement");
        }
        Product oldProduct = storage[x][y];
        storage[x][y] = product;

        // Fire property change
        this.firePropertyChange("storage", oldProduct, product);
    }

    public void removeProduct(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("Invalid storage position");
        }
        Product oldProduct = storage[x][y];
        storage[x][y] = null;

        // Fire property change
        firePropertyChange("storage", oldProduct, null);
    }

    public Product getProduct(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("Invalid storage position");
        }
        return storage[x][y];
    }

    public boolean isPositionEmpty(int x, int y) {
        return getProduct(x, y) == null;
    }

    public Product[][] getStorage() {
        return storage;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
}
