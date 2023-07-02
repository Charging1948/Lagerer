package services;

import view.MainFrame;
import view.SidePanel;

public class ServiceInjector {

    private MainFrame mainFrame;
    private SidePanel sidePanel;

    public ServiceInjector() {
        this.mainFrame = new MainFrame("Title");
        this.sidePanel = new SidePanel();
    }

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

    public SidePanel getSidePanel() {
        return this.sidePanel;
    }
}

