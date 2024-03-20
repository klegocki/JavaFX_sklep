package com.example.sklep;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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


public class ZamowienieController implements Initializable {
    BazaDanych baza = new BazaDanych();
    @FXML
    private TextField adres = new TextField();
    @FXML
    private Label finish = new Label();
    @FXML
    private Label finish2 = new Label();
    @FXML
    private Button wyloguj = new Button();
    @FXML
    private Button ProduktyButton = new Button();
    @FXML
    private Label pseudo=new Label();
    @FXML
    private Label cena_zam = new Label();
    @FXML
    private Label pseudonim_zam = new Label();
    private String previousPseudonim = pseudo.getText();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                        pseudo.setText(baza.zalogowany.getPseudonim());
                        int cena_wszystkich = 0;
                        for (Produkt produkt : baza.koszyk){
                            cena_wszystkich += produkt.cena * produkt.ilosc_zamowionych;
                        }
                        cena_zam.setText(cena_wszystkich + ",00 zl");
                        pseudonim_zam.setText(baza.zalogowany.getPseudonim());
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
}

    @FXML
    public void zamowienie() {
        if(adres.getText().trim().isEmpty()){
            finish2.setText("Prosze wpisać adres");
        }
        else if(baza.zamowienie(adres.getText())) {
            finish2.setText("");
            finish.setText("Transakcja zakończona pomyślnie");
        }
        else{
            finish.setText("");
            finish2.setText("Wystąpił błąd, spróbuj ponownie");
        }
    }
    @FXML
    public void przejdzDoSklepu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ProduktyButton.getScene().getWindow();
        stage.setScene(scene);
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
}
