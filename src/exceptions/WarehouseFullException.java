package exceptions;

public class WarehouseFullException extends StorageException {
    public WarehouseFullException(String message) {
        super(message);
    }
}
