package interfaces;

import model.Position;
import model.Product;

public interface WarehousePanelDelegate {
    void confirmRemoveProduct(Position position);
    void placeProduct(Product product, Position position);
    void shipOutProduct(Product product, Position position);
}
