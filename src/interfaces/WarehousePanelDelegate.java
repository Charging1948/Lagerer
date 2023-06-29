package interfaces;

import model.Position;
import model.Product;

public interface WarehousePanelDelegate {
    void confirmRemoveProduct(Product product, Position position);
}
