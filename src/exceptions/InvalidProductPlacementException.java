package exceptions;

public class InvalidProductPlacementException extends StorageException {
    public InvalidProductPlacementException(String message) {
        super(message);
    }
}
