package model;

import exceptions.InvalidProductPlacementException;
import exceptions.WarehouseFullException;

public class Warehouse extends BaseModel {
    private Product[][] storage;
    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;
    
    /**
     * Enum for the methods that this class provides.
     * Used to generate the property change names.
     */
    public enum Methods {
        STORE,
        REMOVE
    }

    public Warehouse() {
        storage = new Product[WIDTH][HEIGHT];
    }

    /**
     * Stores the given product at the given position. Fires a property change event.
     * @param product The product to store
     * @param position The position to store the product at
     * @throws WarehouseFullException If the warehouse is full
     * @throws InvalidProductPlacementException If the product cannot be placed at the given position
     * @throws IllegalArgumentException If the given position is out of bounds of the warehouse
     */
    public void storeProduct(Product product, Position position) throws WarehouseFullException, InvalidProductPlacementException, IllegalArgumentException{
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("Invalid storage position");
        }
        if (storage[x][y] != null) {
            throw new WarehouseFullException("This position is already occupied");
        }

        // will throw an exception if the product cannot be placed at the given position
        product.isValidPlacement(x, y);

        Product oldProduct = storage[x][y];
        storage[x][y] = product;

        // Fire property change
        this.firePropertyChange(getStoragePropertyChangeName(Methods.STORE, position), oldProduct, product);
    }

    /**
     * Removes the product at the given position. Fires a property change event.
     * @param position The position to remove the product at
     */
    public void removeProduct(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("Invalid storage position");
        }
        Product oldProduct = storage[x][y];
        storage[x][y] = null;

        // Fire property change
        firePropertyChange(getStoragePropertyChangeName(Methods.REMOVE, position), oldProduct, null);
    }

    /**
     * Returns the product at the given position
     * @param position The position to retrieve
     * @return The product at the given position or null, if the position is empty
     * @throws IllegalArgumentException If the given position is out of bounds of the warehouse
     */
    public Product getProduct(Position position) throws IllegalArgumentException {
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            throw new IllegalArgumentException("Invalid storage position");
        }
        return storage[x][y];
    }

    /**
     * Checks if the given position is empty
     * @param position The position to check
     * @return True, if the position is empty, false otherwise
     * @throws IllegalArgumentException If the given position is out of bounds of the warehouse
     */
    public boolean isPositionEmpty(Position position) throws IllegalArgumentException {
        return getProduct(position) == null;
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

    /**
     * Generates a property change name for the storage
     * 
     * @param method The method that was called
     * @param position The position that was changed
     * @return The property change name
     */
    public static String getStoragePropertyChangeName(Methods method, Position position) {
        return String.format("storage_%s_%d_%d", method.toString().toLowerCase(), position.getX(), position.getY());
    }

    // implement method to get position from property change name, return as tuple
    /**
     * Returns the position from the given property change name
     * @param propertyName The property change name
     * @return The position as an array of length 2, where the first element is the x position and the second element is the y position.
     */
    public static Position getPositionFromPropertyChangeName(String propertyName) {
        String[] split = propertyName.split("_");
        int x = Integer.parseInt(split[2]);
        int y = Integer.parseInt(split[3]);

        return new Position(x, y);
    }

    public static Methods getMethodFromPropertyChangeName(String propertyName) {
        String[] split = propertyName.split("_");
        return Methods.valueOf(split[1].toUpperCase());
    }
}
