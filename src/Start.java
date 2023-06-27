import javax.swing.*;
import java.awt.*;

import controller.*;
import exceptions.*;
import model.Paper;
import model.Stone;
import model.Paper.Color;
import model.Paper.Size;
import model.Stone.StoneType;
import model.Stone.Weight;
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
        // OrderController orderController = new OrderController();
        WarehouseController warehouseController = new WarehouseController();

        // Create views
        // ProductPanel productPanel = new ProductPanel(productController);
        BalancePanel balancePanel = new BalancePanel();

        try {
            warehouseController.storeProduct(new Stone(StoneType.GRANITE, Weight.LIGHT), 0, 0);
            warehouseController.storeProduct(new Stone(StoneType.SANDSTONE, Weight.HEAVY), 1, 0);
            warehouseController.storeProduct(new Paper(Color.BLUE, Size.A5), 2, 0);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }

        // Add views to main frame
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLayout(new GridLayout(1, 3));
        mainFrame.add(warehouseController.getWarehousePanel());
        mainFrame.add(balancePanel);

        // Make main frame visible
        mainFrame.setVisible(true);
    }
}
