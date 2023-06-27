package exceptions;

import java.io.IOException;

public class AssetLoadException extends IOException {
    public AssetLoadException(String messageString) {
        super(messageString);
    }
}
