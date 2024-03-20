package com.example.sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField login_logowanie = new TextField();
    @FXML
    private TextField haslo_logowanie = new TextField();
    @FXML
    private TextField login_rej = new TextField();
    @FXML
    private TextField haslo_rej = new TextField();
    @FXML
    private TextField pseudonim_rej = new TextField();
    @FXML
    private Label error = new Label();
    @FXML
    private Label error1 = new Label();
    @FXML
    private Button login = new Button();
    @FXML
    private Button rejestracja = new Button();

    BazaDanych baza = new BazaDanych();
    @FXML
    private void logowanie() throws IOException {
        if(baza.logowanie(login_logowanie.getText(),haslo_logowanie.getText())){

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(scene);
        }
        else if(login_logowanie.getText().trim().isEmpty() || haslo_logowanie.getText().trim().isEmpty() ) {
            error1.setText("Proszę uzupełnić wszystkie pola");
        }

        else{
            error1.setText("Niezgodność loginu, bądź hasła");
        }

    }

    @FXML
    private void rejestracja() throws IOException {

        if(baza.rejestracja(login_rej.getText(),haslo_rej.getText(),pseudonim_rej.getText())){

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) rejestracja.getScene().getWindow();
            stage.setScene(scene);
        }
        else if(login_rej.getText().trim().isEmpty() || haslo_rej.getText().trim().isEmpty() || pseudonim_rej.getText().trim().isEmpty()){
            error.setText("Proszę uzupełnić wszystkie pola");
        }else {
            error.setText("Proszę podać podać inny pseudonim, bądź login ponieważ taki użytkownik już istnieje");
        }
    }
}
