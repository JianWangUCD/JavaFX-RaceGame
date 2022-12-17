package com.example.comp20300javafxproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Simon’s Race Game
 */
public class RaceGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RaceGame.class.getResource("architecture.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JavaFX Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}