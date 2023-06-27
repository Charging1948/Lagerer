package model;

import exceptions.InvalidProductPlacementException;

public class Stone extends Product {
    private StoneType stoneType;
    private Weight weight;

    public enum Weight {
        LIGHT,
        MEDIUM,
        HEAVY
    }

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

    public void setStoneType(StoneType stoneType) {
        this.stoneType = stoneType;
    }

    public Weight getWeight() {
        return this.weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public boolean isValidPlacement(int x, int y) throws InvalidProductPlacementException {
        if (this.weight == Weight.HEAVY && y != 0) {
            throw new InvalidProductPlacementException("Heavy stones can only be stored on the ground floor");
        }
        if (this.weight == Weight.MEDIUM && y == Warehouse.getHeight() - 1) {
            throw new InvalidProductPlacementException("Medium stones cannot be stored on the top floor");
        }
        return true;
    }

    public String getAssetNameString() {
        return getWithAssetBaseString("stone_" + this.stoneType.toString().toLowerCase() + ".png");
    }

    public String getAssetTypeString() {
        return getWithAssetBaseString(Product.TYPE_BASE + this.weight.toString().toLowerCase() + ".png");
    }
}