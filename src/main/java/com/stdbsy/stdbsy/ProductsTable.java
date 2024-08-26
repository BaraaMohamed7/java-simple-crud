package com.stdbsy.stdbsy;

import com.stdbsy.stdbsy.models.Product;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.sql.SQLException;

public class ProductsTable implements Observer {
    private TableView<Product> table;
    private final DbController dbController = DbController.getInstance();

    public ProductsTable() {
    }

    TableView<Product> constructTable() throws SQLException {
        table = new TableView<>();
        table.setEditable(true);
        table.setMinWidth(600);

        TableColumn<Product, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(150);

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        nameCol.setMinWidth(150);

        TableColumn<Product,String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setMinWidth(150);

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setMinWidth(150);

        TableColumn<Product,Button> deleteButtonCol = new TableColumn<>("Delete");
        deleteButtonCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        table.getColumns().addAll(idCol, nameCol, descriptionCol, priceCol, deleteButtonCol);
        ObservableList<Product> products = dbController.getProducts();
        table.setItems(products);

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setName(event.getNewValue());
            try {
                dbController.updateProduct(product.getName(), product.getDescription(), product.getPrice(), product.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setDescription(event.getNewValue());
            try {
                dbController.updateProduct(product.getName(), product.getDescription(), product.getPrice(), product.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceCol.setOnEditCommit(event -> {
            Product product = event.getTableView().getItems().get(event.getTablePosition().getRow());
            product.setPrice(event.getNewValue());
            try {
                dbController.updateProduct(product.getName(), product.getDescription(), product.getPrice(), product.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        return table;
    }


    @Override
    public void update(ObservableList<Product> observableList) {
        table.setItems(observableList);
    }
}
