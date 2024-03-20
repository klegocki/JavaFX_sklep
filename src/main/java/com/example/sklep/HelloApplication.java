package com.example.sklep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

        @Override
        public void start(Stage stage) throws IOException {

            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent menuRoot = menuLoader.load();
            Scene menuScene = new Scene(menuRoot);
            stage.setScene(menuScene);
            stage.setTitle("Sklep RTV");
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }



