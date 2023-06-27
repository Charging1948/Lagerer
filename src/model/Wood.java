package model;

public class Wood extends Product {
    private Type type;
    private Shape shape;

    public enum Type {
        OAK,
        PINE,
        BEECH
    }

    public enum Shape {
        STUMP,
        PLANK,
        BEAM
    }

    public Wood(Type type, Shape shape) {
        super();
        this.type = type;
        this.shape = shape;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Shape getShape() {
        return this.shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public String getAssetNameString() {
        return getWithAssetBaseString("wood_" + this.shape.toString().toLowerCase() + ".png");
    }

    public String getAssetTypeString() {
        return getWithAssetBaseString(Product.TYPE_BASE + this.type.toString().toLowerCase() + ".png");
    }

    public boolean isValidPlacement(int x, int y) {
        return true;
    }
}