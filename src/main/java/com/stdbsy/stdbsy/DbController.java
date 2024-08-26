package com.stdbsy.stdbsy;

import com.stdbsy.stdbsy.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DbController implements Subject {

    private static DbController instance;
    private static final ArrayList<Observer> observers = new ArrayList<>();

    private DbController() {}

    public static DbController getInstance() {
        if (instance == null) {
            instance = new DbController();
        }
        return instance;
    }

    Statement getStatement() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/products.db");
        return conn.createStatement();
    }

    public void addProduct(String productName, String productDescription, double productPrice) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/products.db");
       PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO products (name,description, price) VALUES (?,?,?)");
        preparedStatement.setString(1, productName);
        preparedStatement.setString(2, productDescription);
        preparedStatement.setDouble(3, productPrice);
        preparedStatement.executeUpdate();
       notifyObservers();
       conn.close();
    }

    public void deleteProduct(int id) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/products.db");
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM products WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        notifyObservers();
        conn.close();
    }

    public void updateProduct(String productName, String productDescription, double productPrice ,int id) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/products.db");
        PreparedStatement preparedStatement = conn.prepareStatement("Update products SET name=?, description=?, price=? WHERE id = ?");
        preparedStatement.setString(1, productName);
        preparedStatement.setString(2, productDescription);
        preparedStatement.setDouble(3, productPrice);
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();


    }

    public ObservableList<Product> getProducts() throws SQLException {
        ObservableList<Product> productsList = FXCollections.observableArrayList();

        Statement statement=  getStatement();
        ResultSet resultSet = statement.executeQuery("select * from products");
        while (resultSet.next()) {
            Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getDouble("price"));
            productsList.add(product);
        }
        statement.close();
        return productsList;
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> {
            try {
                observer.update(getProducts());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

