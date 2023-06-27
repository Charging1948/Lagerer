package model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import exceptions.AssetLoadException;
import exceptions.InvalidProductPlacementException;

public abstract class Product {

    private static final int PICTURE_WIDTH = 1200;
    private static final int PICTURE_HEIGHT = PICTURE_WIDTH;
    private static final int SCALED_PICTURE_WIDTH = 200;
    private static final int SCALED_PICTURE_HEIGHT = SCALED_PICTURE_WIDTH;
    private static final int LABEL_PICTURE_WIDTH = 2000;
    private static final int LABEL_PICTURE_HEIGHT = 400;
    private static final int LABEL_PICTURE_X_OFFSET = -(LABEL_PICTURE_WIDTH / 2 - PICTURE_WIDTH / 2 - 100);
    private static final int LABEL_PICTURE_Y_OFFSET = LABEL_PICTURE_HEIGHT / 2 + PICTURE_HEIGHT / 2;
    public static final String ASSET_BASE = "assets/";
    public static final String TYPE_BASE = "type/";
    public static final String EMPTY_ASSET = "empty.png";

    public abstract boolean isValidPlacement(int x, int y) throws InvalidProductPlacementException;

    public abstract String getAssetNameString();

    public abstract String getAssetTypeString();

    public static String getWithAssetBaseString(String assetName) {
        return ASSET_BASE + assetName;
    }

    public ImageIcon getImageIcon() throws AssetLoadException {
        // Load both images
        BufferedImage image1 = null;
        BufferedImage image2 = null;
        try {
            URL url1 = this.getClass().getClassLoader().getResource(getAssetNameString());
            URL url2 = this.getClass().getClassLoader().getResource(getAssetTypeString());
            image1 = ImageIO.read(url1);
            image2 = ImageIO.read(url2);
        } catch (IOException e) {
           throw new AssetLoadException(e.getMessage()); 
        }

        // Create a new image with the same size as the first image
        BufferedImage combined = new BufferedImage(PICTURE_WIDTH, PICTURE_HEIGHT, BufferedImage.TYPE_INT_ARGB);



        // Draw both images on the new image
        Graphics g = combined.getGraphics();
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, LABEL_PICTURE_X_OFFSET, LABEL_PICTURE_Y_OFFSET, null);

        Image scaledImage = combined.getScaledInstance(SCALED_PICTURE_WIDTH, SCALED_PICTURE_HEIGHT, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public static ImageIcon getEmptyImageIcon() throws AssetLoadException {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Product.class.getClassLoader().getResource(getWithAssetBaseString(EMPTY_ASSET)));
        } catch (IOException e) {
            throw new AssetLoadException(e.getMessage());
        }

        BufferedImage bufferedImage = new BufferedImage(PICTURE_WIDTH, PICTURE_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, LABEL_PICTURE_X_OFFSET, LABEL_PICTURE_Y_OFFSET, null);

        Image scaledImage = bufferedImage.getScaledInstance(SCALED_PICTURE_WIDTH, SCALED_PICTURE_HEIGHT, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }
}