package exceptions;

public class StorageSpaceOccupiedException extends StorageException {
    public StorageSpaceOccupiedException(String message) {
        super(message);
    }
}