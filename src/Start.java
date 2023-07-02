import javax.swing.*;
import java.awt.*;

import controller.*;
import services.ServiceInjector;
import view.*;

public class Start {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /*
     * Create the GUI and show it. For thread safety, this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {

        // Inject services
        ServiceInjector serviceInjector = new ServiceInjector();

        // Create controllers
        // ProductController productController = new ProductController();
        AppController appController = new AppController();

               // Get controllers
        WarehouseController warehouseController = appController.getWarehouseController();
        OrderController orderController = appController.getOrderController();
        BalanceController balanceController = appController.getBalanceController();
        StatusController statusController = appController.getStatusController();

        // Instantiate SidePanel and add SubPanels
        SidePanel sidePanel = serviceInjector.getSidePanel();
        sidePanel.add(balanceController.getBalancePanel(), BorderLayout.SOUTH);
        sidePanel.add(orderController.getOrderPanel(), BorderLayout.CENTER);

        // Add views to main frame
        MainFrame mainFrame = serviceInjector.getMainFrame();
        mainFrame.add(warehouseController.getWarehousePanel(), BorderLayout.CENTER);
        mainFrame.add(statusController.getStatusPanel(), BorderLayout.SOUTH);
        mainFrame.add(sidePanel, BorderLayout.EAST);

        // Make main frame visible
        mainFrame.setVisible(true);
    }
}
