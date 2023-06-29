package controller;

import java.beans.PropertyChangeEvent;
import view.StatusPanel;

public class StatusController {

    private String status;
    private StatusPanel statusPanel;

    StatusController() {
        this.status = "Waiting for action...";
        this.statusPanel = new StatusPanel(status);
    }

    public void setStatus(String status) {
        this.status = status;
        this.statusPanel.setStatus(status);
    }

    public StatusPanel getStatusPanel() {
        return this.statusPanel;
    }

}
