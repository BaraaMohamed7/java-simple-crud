package com.stdbsy.stdbsy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        HomeScreen homeScreen = new HomeScreen();
        Scene scene = homeScreen.getScene();
//        scene.getStylesheets().add("style.css");
        stage.setWidth(900);
        stage.setHeight(600);
        stage.setTitle("Simple CRUD App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}