package controller;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductController extends BaseController {
    private List<Product> productList;

    public ProductController() {
        this.productList = new ArrayList<>();
    }

    public void addNewProduct(Product product) {
        this.productList.add(product);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }


}