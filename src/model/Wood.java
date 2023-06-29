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

    public boolean isValidPlacement(int x, int y) {
        return true;
    }

    public String toShortString() {
        return String.format("Wood:%s", this.shape.toString());
    }

    @Override
    public String toString() {
        String lsp = System.lineSeparator();
        return String.format("Wood:%s %n %s %n", lsp, this.shape, lsp, this.type);
    }

    public String toHTMLString() {
        return "<html><body><h1>Wood</h1><br>" + this.shape + "<br>" + this.type + "</body></html>";
    }
}