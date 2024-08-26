package com.stdbsy.stdbsy;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.SQLException;

public class HomeScreen {
    Text title;
    Button addButton;
    ProductsTable productsTable;
    GridPane grid;

    DbController dbController = DbController.getInstance();

    public HomeScreen() throws SQLException {
        initControls();
        constructScene();
        initActions();
    }

    void initControls(){
        title = new Text("Products");
        addButton = new Button("Add Product");
        addButton.setMinWidth(400);
        productsTable = new ProductsTable();
        dbController.registerObserver(productsTable);
        grid = new GridPane();
    }

    void constructScene() throws SQLException {
        grid.add(title, 0, 0);
        grid.add(addButton, 2, 0);
        grid.add(productsTable.constructTable(),0,2, 6,6);

        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
    }

    void initActions(){
        addButton.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.setTitle("Add Product");
            addStage.setWidth(400);
            addStage.setHeight(500);

            AddProductScreen addProductScreen = new AddProductScreen();
            addStage.setScene(addProductScreen.getScene());
            addStage.show();
        });
    }

    Scene getScene() {
        return new Scene(grid);
    }
}
