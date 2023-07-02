package model.helpers;

import java.util.HashMap;
import java.util.Map;

import model.Order;
import model.Paper;
import model.Stone;
import model.Wood;

public class TypeMaps {

    public static Map<String, Order.OrderType> getOrderTypeMap() {
        Map<String, Order.OrderType> orderTypeMap = new HashMap<>();
        orderTypeMap.put("Einlagerung", Order.OrderType.INBOUND);
        orderTypeMap.put("Auslagerung", Order.OrderType.OUTBOUND);
        return orderTypeMap;
    }

    public static Map<String, Paper.Color> getPaperColorMap() {
        Map<String, Paper.Color> paperColorMap = new HashMap<>();
        paperColorMap.put("Weiss", Paper.Color.WHITE);
        paperColorMap.put("Gruen", Paper.Color.GREEN);
        paperColorMap.put("Blau", Paper.Color.BLUE);
        return paperColorMap;
    }

    public static Map<String, Paper.Size> getPaperSizeMap() {
        Map<String, Paper.Size> paperSizeMap = new HashMap<>();
        paperSizeMap.put("A3", Paper.Size.A3);
        paperSizeMap.put("A4", Paper.Size.A4);
        paperSizeMap.put("A5", Paper.Size.A5);
        return paperSizeMap;
    }

    public static Map<String, Wood.Type> getWoodTypeMap() {
        Map<String, Wood.Type> woodTypeMap = new HashMap<>();
        woodTypeMap.put("Eiche", Wood.Type.OAK);
        woodTypeMap.put("Kiefer", Wood.Type.PINE);
        woodTypeMap.put("Buche", Wood.Type.BEECH);
        return woodTypeMap;
    }

    public static Map<String, Wood.Shape> getWoodShapeMap() {
        Map<String, Wood.Shape> woodShapeMap = new HashMap<>();
        woodShapeMap.put("Scheit", Wood.Shape.STUMP);
        woodShapeMap.put("Bretter", Wood.Shape.PLANK);
        woodShapeMap.put("Balken", Wood.Shape.BEAM);
        return woodShapeMap;
    }

    public static Map<String, Stone.StoneType> getStoneTypeMap() {
        Map<String, Stone.StoneType> stoneTypeMap = new HashMap<>();
        stoneTypeMap.put("Marmor", Stone.StoneType.MARBLE);
        stoneTypeMap.put("Granit", Stone.StoneType.GRANITE);
        stoneTypeMap.put("Sandstein", Stone.StoneType.SANDSTONE);
        return stoneTypeMap;
    }

    public static Map<String, Stone.Weight> getStoneWeightMap() {
        Map<String, Stone.Weight> stoneWeightMap = new HashMap<>();
        stoneWeightMap.put("Leicht", Stone.Weight.LIGHT);
        stoneWeightMap.put("Mittel", Stone.Weight.MEDIUM);
        stoneWeightMap.put("Schwer", Stone.Weight.HEAVY);
        return stoneWeightMap;
    }

    public static Map<String, String> getProductTypeMap() {
        Map<String, String> productTypeMap = new HashMap<>();
        productTypeMap.put("Papier", "Paper");
        productTypeMap.put("Holz", "Wood");
        productTypeMap.put("Stein", "Stone");
        return productTypeMap;
    }
}

