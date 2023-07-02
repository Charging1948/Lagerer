package model;

import java.util.Objects;

public class Paper extends Product {
    private final Color color;
    private final Size size;
    
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

    public Size getSize() {
        return this.size;
    }

    public boolean isValidPlacement(int x, int y) {
        return true;
    }

    public String toShortString() {
        return String.format("Paper: %s %s", this.color.toString(), this.size.toString());
    }

    @Override
    public String toString() {
        String lsp = System.lineSeparator();
        return String.format("Paper:%s %n %s %n", lsp, this.color, lsp, this.size);
    }

    public String toHTMLString() {
        return "<html><body><h2>Paper</h2><br>" + this.color + "<br>" + this.size + "</body></html>";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Paper paper = (Paper) obj;
        return color == paper.color && size == paper.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size);
    }
}