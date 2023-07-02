package interfaces;

import model.Position;
import model.Product;

public interface WarehouseControllerDelegate {
    void productSentOut(Product product, Position position);
    void productRemoved(Product product);
    void productStored(Product product, Position position);
}
