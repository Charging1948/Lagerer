package controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class BaseController implements IController {
    private PropertyChangeSupport pcs;

    public BaseController() {
        this.pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

}
