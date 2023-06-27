package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class BaseModel implements PropertyChangeListener{
    private PropertyChangeSupport pcs; 
    public BaseModel() {
        this.pcs = new PropertyChangeSupport(this);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        this.pcs.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    } 
}
