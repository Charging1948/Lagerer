You are now an it student. You are tasked to create an application in the java Programming Language. More specifically you are required to build a small game with a gui using only the libraries contained in the jdk17 and the swing framework for the gui. You will need to have simple warehouse, where you are the manager. There is a button for loading a new order (incoming or outgoing) from a previous defined list. The previous defined list will look like the following:

```java
public class Orders {
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
	private int lastOrder = -1;

	public String[] getNextOrder() {
		lastOrder++;
		return data[lastOrder];
	}

	public static void main(String[] args) {
		// just for testing purpose
		Orders orders = new Orders();
		String[] order = orders.getNextOrder();
		for (String cell : order) {
			System.out.print(cell);
			System.out.print(";");
		}
		System.out.println(); // new line
		order = orders.getNextOrder();
		for (String cell : order) {
			System.out.print(cell);
			System.out.print(";");
		}
	}
}
```

As you can see all the names are currently in german, and not properly typed. You will have to change that. For that you will first create classes for the different types of items.
The Wood class could look like this:
```java
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
}
```

The Paper class could look like this:
```java
public class Paper extends Product {
    private Color color;
    private Size size;

    public enum Color {
        WHITE,
        BLUE,
        GREEN
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
}
```

The Stone class could look like this:
```java
public class Stone extends Product {
    private Type type;
    private Weight weight;

    public enum Type {
        GRANITE,
        SANDSTONE,
        MARBLE
    }

    public enum Weight {
        LIGHT,
        MIDDLE,
        HEAVY
    }

    public Stone(Type type, Weight weight) {
        super();
        this.type = type;
        this.weight = weight;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Weight getWeight() {
        return this.weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}
```

Now you will have to implement a parent class for all the products. This class will be called Product and will look like this:
```java
public class Product {
    public abstract boolean isValidPlacement(int x, int y);
}
```

This isValidPlacement should of course be implemented in the different product classes. But only the stone class has specific requirements for placement. The other classes can just return true. The stone classes isValidPlacement should look like this:
```java
public boolean isValidPlacement(int x, int y) {
    return x % 2 == 0 && y % 2 == 0;
}
```

Now you will have to implement a class for the different types of orders. The class for the order could look like this:
```java
public class Order {
    private Product product;
    private double reward;

    public Order(Product product, double reward) {
        this.product = product;
        this.reward = reward;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getReward() {
        return this.reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}
```

And there also should be something to store the orderhistory. This could look like this:
```java
public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrders() {
        return this.orders;
    }
}
```

And somewhere you will have to store the current state of the warehouse.