package model;

public class Paper extends Product {
    private Color color;
    private Size size;
    
    public enum Color {
        WHITE,
        GREEN,
        BLUE
    }

    public enum Size {
        A3,
        A4,
        A5
    }

    public Paper(Color color, Size size) {
        super();
        this.color = color;
        this.size = size;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getAssetNameString() {
        return getWithAssetBaseString("paper_" + this.color.toString().toLowerCase() + ".png");
    }

    public String getAssetTypeString() {
        return getWithAssetBaseString(Product.TYPE_BASE + this.size.toString().toLowerCase() + ".png");
    }

    public boolean isValidPlacement(int x, int y) {
        return true;
    }
}