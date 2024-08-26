package com.stdbsy.stdbsy;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

public class AddProductScreen {

    Label nameLabel;
    Label descriptionLabel;
    Label priceLabel;

    TextField nameTextField;
    TextField descriptionTextField;
    TextField priceTextField;

    Button confirmButton;

    GridPane pane;

    DbController dbController = DbController.getInstance();


    public AddProductScreen() {
        initControls();
        constructScene();
        initActions();
    }
    void  initControls() {
        nameLabel = new Label("Name");
        descriptionLabel = new Label("Description");
        priceLabel = new Label("Price");


        nameTextField = new TextField();
        descriptionTextField = new TextField();
        priceTextField = new TextField();


        confirmButton = new Button("Confirm");

        pane = new GridPane();
    }
    void  constructScene() {
        pane.add(nameLabel, 0, 0);
        pane.add(descriptionLabel, 0, 1);
        pane.add(priceLabel, 0, 2);
        pane.add(nameTextField, 1, 0,3,1);
        pane.add(descriptionTextField, 1, 1, 3 ,1);
        pane.add(priceTextField, 1, 2, 3 ,1);
        pane.add(confirmButton, 1, 3,2,1);

        pane.setAlignment(Pos.CENTER);
        pane.setVgap(20);
        pane.setHgap(20);
    }

    void initActions() {
        confirmButton.setOnAction(e -> {
            try {
                dbController.addProduct(nameTextField.getText(), descriptionTextField.getText(), Double.parseDouble(priceTextField.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    Scene getScene() {
        return new Scene(pane);
    }
}
