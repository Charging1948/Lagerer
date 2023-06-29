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

    public boolean isValidPlacement(int x, int y) {
        return true;
    }

    public String toShortString() {
        return String.format("Paper:%s", this.color.toString());
    }

    @Override
    public String toString() {
        String lsp = System.lineSeparator();
        return String.format("Paper:%s %n %s %n", lsp, this.color, lsp, this.size);
    }

    public String toHTMLString() {
        return "<html><body><h1>Paper</h1><br>" + this.color + "<br>" + this.size + "</body></html>";
    }
}