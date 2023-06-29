import javax.swing.*;
import java.awt.*;

import controller.*;
import exceptions.*;
import model.*;
import model.Stone.*;
import model.Paper.*;
import view.*;

public class Start {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {

        // Create controllers
        // ProductController productController = new ProductController();
        AppController appController = new AppController();

        // Get controllers
        WarehouseController warehouseController = appController.getWarehouseController();
        OrderController orderController = appController.getOrderController();
        BalanceController balanceController = appController.getBalanceController();
        StatusController statusController = appController.getStatusController();

        try {
            appController.getWarehouseController().storeProduct(new Stone(StoneType.GRANITE, Weight.LIGHT), new Position(0,0));
            appController.getWarehouseController().storeProduct(new Stone(StoneType.SANDSTONE, Weight.HEAVY), new Position(1,0));
            appController.getWarehouseController().storeProduct(new Paper(Paper.Color.BLUE, Size.A5), new Position(2,0));

        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }

        // Add views to main frame
        MainFrame mainFrame = new MainFrame();
        mainFrame.add(warehouseController.getWarehousePanel(), BorderLayout.CENTER);
        mainFrame.add(orderController.getOrderPanel(), BorderLayout.EAST);
        mainFrame.add(balanceController.getBalancePanel(), BorderLayout.WEST);
        mainFrame.add(statusController.getStatusPanel(), BorderLayout.SOUTH);

        // Make main frame visible
        mainFrame.setVisible(true);
    }
}
