package com.example.sklep;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Controller implements Initializable {



    @FXML
    private Button koszykButton=new Button();
    @FXML
    public Button szczegoly0 = new Button();
    @FXML
    public Button szczegoly1 = new Button();
    @FXML
    public Button szczegoly3 = new Button();
    @FXML
    public Button szczegoly4 = new Button();
    @FXML
    public Button szczegoly5 = new Button();
    @FXML
    public Button szczegoly6 = new Button();
    @FXML
    public Button szczegoly7 = new Button();
    @FXML
    public Button szczegoly2 = new Button();
    @FXML
    private Button wyloguj = new Button();
    @FXML
    private Label pseudo = new Label();
    BazaDanych baza = new BazaDanych();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    pseudo.setText(baza.zalogowany.getPseudonim());
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

@FXML
public void Wyloguj() throws IOException{

        baza.wylogowany();

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) wyloguj.getScene().getWindow();
    stage.setScene(scene);

}

    @FXML
    public void przejdzDoProduktu1(ActionEvent event) throws IOException {

        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        switch (buttonId) {
            case "0":
                baza.y = Integer.parseInt(szczegoly7.getId());
                break;
            case "1":
                baza.y = Integer.parseInt(szczegoly6.getId());
                break;
            case "2":
                baza.y = Integer.parseInt(szczegoly2.getId());
                break;
            case "3":
                baza.y = Integer.parseInt(szczegoly0.getId());
                break;
            case "4":
                baza.y = Integer.parseInt(szczegoly4.getId());
                break;
            case "5":
                baza.y = Integer.parseInt(szczegoly5.getId());
                break;
            case "6":
                baza.y = Integer.parseInt(szczegoly3.getId());
                break;
            case "7":
                baza.y = Integer.parseInt(szczegoly1.getId());
                break;

        }



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produkt.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) szczegoly0.getScene().getWindow();
        stage.setScene(scene);


    }
    @FXML
    public void przejdzDoKoszyka() throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("koszyk.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) koszykButton.getScene().getWindow();

        stage.setScene(scene);
    }


}
