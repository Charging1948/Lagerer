package model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Order;
import model.Paper;
import model.Product;
import model.Stone;
import model.Wood;
import model.Order.OrderType;

public class DummyOrderGenerator {
    Map<String, Paper.Color> paperColorMap = TypeMaps.getPaperColorMap();
    Map<String, Paper.Size> paperSizeMap = TypeMaps.getPaperSizeMap();
    Map<String, Wood.Type> woodTypeMap = TypeMaps.getWoodTypeMap();
    Map<String, Wood.Shape> woodShapeMap = TypeMaps.getWoodShapeMap();
    Map<String, Stone.StoneType> stoneTypeMap = TypeMaps.getStoneTypeMap();
    Map<String, Stone.Weight> stoneWeightMap = TypeMaps.getStoneWeightMap();
    Map<String, String> productTypeMap = TypeMaps.getProductTypeMap();
    Map<String, OrderType> orderTypeMap = TypeMaps.getOrderTypeMap();

    private final String[][] data = {
			{ "1", "Einlagerung", "Papier", "Weiss", "A4", "200" },
			{ "2", "Einlagerung", "Papier", "Blau", "A5", "300" },
			{ "3", "Einlagerung", "Holz", "Kiefer", "Bretter", "200" },
			{ "4", "Einlagerung", "Holz", "Buche", "Balken", "500" },
			{ "5", "Einlagerung", "Holz", "Eiche", "Scheit", "200" },
			{ "6", "Einlagerung", "Papier", "Blau", "A5", "200" },
			{ "7", "Einlagerung", "Papier", "Blau", "A5", "200" },
			{ "8", "Einlagerung", "Stein", "Marmor", "Mittel", "400" },
			{ "9", "Einlagerung", "Stein", "Granit", "Schwer", "500" },
			{ "10", "Einlagerung", "Stein", "Sandstein", "Leicht", "200" },
			{ "11", "Auslagerung", "Papier", "Blau", "A5", "1000" },
			{ "12", "Auslagerung", "Holz", "Eiche", "Scheit", "1200" },
			{ "13", "Auslagerung", "Stein", "Marmor", "Mittel", "1000" },
			{ "14", "Auslagerung", "Papier", "Weiss", "A5", "1500" },
			{ "15", "Einlagerung", "Holz", "Eiche", "Balken", "400" },
			{ "16", "Einlagerung", "Holz", "Eiche", "Scheit", "600" },
			{ "17", "Einlagerung", "Holz", "Buche", "Scheit", "200" },
			{ "18", "Einlagerung", "Stein", "Granit", "Leicht", "400" },
			{ "19", "Einlagerung", "Papier", "Blau", "A3", "200" },
			{ "20", "Einlagerung", "Papier", "Blau", "A5", "200" },
			{ "21", "Einlagerung", "Holz", "Eiche", "Scheit", "600" },
			{ "22", "Einlagerung", "Holz", "Buche", "Balken", "600" },
			{ "23", "Einlagerung", "Stein", "Sandstein", "Schwer", "200" },
			{ "24", "Einlagerung", "Stein", "Granit", "Schwer", "600" },
			{ "25", "Einlagerung", "Holz", "Buche", "Bretter", "400" },
			{ "26", "Einlagerung", "Holz", "Buche", "Scheit", "200" },
			{ "27", "Auslagerung", "Holz", "Buche", "Scheit", "1000" },
			{ "28", "Auslagerung", "Papier", "Blau", "A5", "1200" },
			{ "29", "Auslagerung", "Stein", "Granit", "Schwer", "1500" },
			{ "30", "Auslagerung", "Holz", "Buche", "Balken", "1000" },
			{ "31", "Auslagerung", "Stein", "Sandstein", "Schwer", "1300" },
			{ "32", "Einlagerung", "Stein", "Granit", "Schwer", "400" },
			{ "33", "Einlagerung", "Stein", "Marmor", "Mittel", "600" },
			{ "34", "Einlagerung", "Stein", "Granit", "Leicht", "400" },
			{ "35", "Einlagerung", "Stein", "Granit", "Leicht", "400" },
			{ "36", "Einlagerung", "Papier", "Weiss", "A4", "400" },
			{ "37", "Einlagerung", "Stein", "Granit", "Leicht", "400" },
			{ "38", "Einlagerung", "Holz", "Buche", "Bretter", "600" },
			{ "39", "Einlagerung", "Holz", "Kiefer", "Bretter", "600" },
			{ "40", "Einlagerung", "Stein", "Sandstein", "Leicht", "400" },
			{ "41", "Auslagerung", "Papier", "Weiss", "A4", "1000" },
			{ "42", "Auslagerung", "Stein", "Marmor", "Mittel", "1200" },
			{ "43", "Auslagerung", "Holz", "Buche", "Bretter", "1100" },
			{ "44", "Auslagerung", "Papier", "Weiss", "A4", "1500" },
			{ "45", "Auslagerung", "Holz", "Kiefer", "Bretter", "1000" },
			{ "46", "Auslagerung", "Stein", "Sandstein", "Leicht", "1200" },
			{ "47", "Auslagerung", "Holz", "Kiefer", "Bretter", "1100" },
			{ "48", "Einlagerung", "Papier", "Gruen", "A4", "400" }
	};
    private List<Order> orders;
    private int currentIndex = 0;

    public DummyOrderGenerator() {
        this.orders = generateOrdersFromData();
    }

    private List<Order> generateOrdersFromData() {
    List<Order> orders = new ArrayList<>();
    for (String[] row : data) {
        OrderType orderType = orderTypeMap.get(row[1]);
        String productType = productTypeMap.get(row[2]);
        Product product;
        switch (productType) {
            case "Paper":
                product = new Paper(paperColorMap.get(row[3]), paperSizeMap.get(row[4]));
                break;
            case "Wood":
                product = new Wood(woodTypeMap.get(row[3]), woodShapeMap.get(row[4]));
                break;
            case "Stone":
                product = new Stone(stoneTypeMap.get(row[3]), stoneWeightMap.get(row[4]));
                break;
            default:
                throw new IllegalArgumentException("Invalid product type: " + productType);
        }
        int reward = Integer.parseInt(row[5]);
        orders.add(new Order(product, reward, orderType));
    }
    return orders;
}

    public Order getNextOrder() {
        if (currentIndex >= orders.size()) {
            currentIndex = 0; // start from the beginning
        }
        return orders.get(currentIndex++);
    }
}

