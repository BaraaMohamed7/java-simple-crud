package com.stdbsy.stdbsy.models;

import com.stdbsy.stdbsy.DbController;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private Button deleteButton;

    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.deleteButton = new Button("Delete");

        this.deleteButton.setOnAction(e -> {
            try {
                DbController.getInstance().deleteProduct(this.getId());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
}

