#!/bin/bash

# Erstelle das Modell-Verzeichnis und die entsprechenden Dateien
mkdir -p src/model
touch src/model/Product.java
touch src/model/Paper.java
touch src/model/Wood.java
touch src/model/Stone.java
touch src/model/Order.java
touch src/model/Warehouse.java

# Erstelle das View-Verzeichnis und die entsprechenden Dateien
mkdir -p src/view
touch src/view/MainFrame.java
touch src/view/WarehousePanel.java
touch src/view/OrderPanel.java
touch src/view/BalancePanel.java

# Erstelle das Controller-Verzeichnis und die entsprechenden Dateien
mkdir -p src/controller
touch src/controller/WarehouseController.java
touch src/controller/OrderController.java