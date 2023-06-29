package model;

import exceptions.InvalidProductPlacementException;

public abstract class Product {

    public abstract boolean isValidPlacement(int x, int y) throws InvalidProductPlacementException;

    public abstract String toHTMLString();

}