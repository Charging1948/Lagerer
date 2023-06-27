package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import exceptions.AssetLoadException;
import model.Product;
import model.Warehouse;

import java.awt.GridLayout;

public class WarehousePanel extends JPanel {

    private static final String EMPTY_IMG = "assets/empty.png";
    private JButton[][] grid;

    public WarehousePanel(int height, int width) {
        this.setLayout(new GridLayout(height, width));
        this.grid = new JButton[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ImageIcon icon = new ImageIcon(EMPTY_IMG);

                JButton button = new JButton(icon);
                button.addActionListener(e -> {
                    // Handle button press here
                    // This could be something like warehouseController.storeProduct(i, j, product)
                });
                this.grid[i][j] = button;
                this.add(button);
            }
        }
    }

    public void updatePanel(Warehouse warehouse) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Product[][] storage = warehouse.getStorage();
                for (int i = 0; i < storage.length; i++) {
                    for (int j = 0; j < storage[i].length; j++) {
                        Product product = storage[i][j];
                        JButton button = grid[i][j]; // Assuming buttons is a 2D array storing your JButtons

                        // If the slot has a product, update the button's icon
                        try {
                            if (product != null) {
                                ImageIcon icon = product.getImageIcon(); // Get the product's icon
                                button.setIcon(icon);
                            } else {
                                // If the slot is empty, reset the button's icon
                                button.setIcon(Product.getEmptyImageIcon());
                            }
                        } catch (AssetLoadException e) {
                            e.printStackTrace();
                        }
                    }
                }
                revalidate();
                repaint();
            }
        });
    }

}