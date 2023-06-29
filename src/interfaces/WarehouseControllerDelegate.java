package interfaces;

import model.Position;
import model.Product;

public interface WarehouseControllerDelegate {
    void productRemoved(Product product, Position position);
}
