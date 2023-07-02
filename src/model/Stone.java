package model;

import java.util.Objects;

import exceptions.InvalidProductPlacementException;

public class Stone extends Product {
    private final StoneType stoneType;
    private final Weight weight;

    /*
     * The weight of the stone determines where it can be stored in the warehouse.
     */
    public enum Weight {
        LIGHT,
        MEDIUM,
        HEAVY
    }
    /* 
     * The type of stone. Doesn't affect the storage.
     */
    public enum StoneType {
        MARBLE,
        GRANITE,
        SANDSTONE
    }

    public Stone(StoneType stoneType, Weight weight) {
        super();
        this.stoneType = stoneType;
        this.weight = weight;
    }

    public StoneType getStoneType() {
        return this.stoneType;
    }

    public Weight getWeight() {
        return this.weight;
    }

    /*
     * Check if the stone can be stored at the given position.
     */
    public boolean isValidPlacement(int x, int y) throws InvalidProductPlacementException {
        if (this.weight == Weight.HEAVY && x != (Warehouse.getHeight() - 1)) {
            throw new InvalidProductPlacementException("Heavy stones can only be stored on the ground floor");
        }
        if (this.weight == Weight.MEDIUM && x == 0) {
            throw new InvalidProductPlacementException("Medium stones cannot be stored on the top floor");
        }
        return true;
    }

    public String toShortString() {
        return String.format("Stone: %s %s", this.weight.toString(), this.stoneType.toString());
    }
    
    @Override
    public String toString() {
        String lsp = System.lineSeparator();
        return String.format("Stone:%s %n %s %n", lsp, this.weight, lsp, this.stoneType);
    }

    public String toHTMLString() {
        return "<html><body><h2>Stone</h2><br>" + this.stoneType + "<br>" + this.weight + "</body></html>";
    }

    /*
     * Two stones are equal if they have the same type and weight.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Stone stone = (Stone) obj;
        return stoneType == stone.stoneType && weight == stone.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stoneType, weight);
    }

}