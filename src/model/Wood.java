package model;

import java.util.Objects;

public class Wood extends Product {
    private final Type type;
    private final Shape shape;

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

    public Shape getShape() {
        return this.shape;
    }

    public boolean isValidPlacement(int x, int y) {
        return true;
    }

    public String toShortString() {
        return String.format("Wood: %s %s", this.shape.toString(), this.type.toString());
    }

    @Override
    public String toString() {
        String lsp = System.lineSeparator();
        return String.format("Wood:%s %n %s %n", lsp, this.shape, lsp, this.type);
    }

    public String toHTMLString() {
        return "<html><body><h2>Wood</h2><br>" + this.shape + "<br>" + this.type + "</body></html>";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Wood wood = (Wood) obj;
        return type == wood.type && shape == wood.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, shape);
    }
}