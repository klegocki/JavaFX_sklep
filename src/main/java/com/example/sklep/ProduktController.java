package com.example.sklep;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ProduktController implements Initializable {

    BazaDanych baza = new BazaDanych();
    @FXML
    private Label cena = new Label();
    @FXML
    private Label ilosc = new Label();
    @FXML
    private Label opis = new Label();
    @FXML
    private Label tytul = new Label();
    @FXML
    private ImageView zdjecie = new ImageView();
    private String previousCena = cena.getText();
    @FXML
    private Label pseudo = new Label();
    @FXML
    private TextField ilosc_zamow = new TextField();
    @FXML
    private Button koszykButton = new Button();
    @FXML
    private Button wyloguj = new Button();
    @FXML
    private Button ProduktyButton = new Button();
    @FXML
    public void updateData() {
        cena.setText(Integer.toString(baza.produkty.get(baza.getY()).cena));
        ilosc.setText(Integer.toString(baza.produkty.get(baza.getY()).ilosc));
        opis.setText(baza.produkty.get(baza.getY()).opis);
        tytul.setText(baza.produkty.get(baza.getY()).nazwa);
        String imagePath = getClass().getResource("/screeny_sklep/" + baza.produkty.get(baza.getY()).zdjecie).toExternalForm();
        Image image = new Image(imagePath);
        zdjecie.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicInteger x = new AtomicInteger();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    pseudo.setText(baza.zalogowany.getPseudonim());
                    String currentCena = cena.getText();
                    if (!currentCena.equals(previousCena)) {
                        updateData();
                        previousCena = currentCena;
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    @FXML
    public void przejdzDoSklepu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ProduktyButton.getScene().getWindow();
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
    @FXML
    public void DodajDoKoszyka() throws IOException {

        Produkt wybranyProdukt = baza.produkty.get(baza.getY());
        boolean jestDuplikat = false;

        for (Produkt p : baza.koszyk) {
            System.out.println();
            if (p.nazwa.equals(wybranyProdukt.nazwa)) {
                jestDuplikat = true;
                p.ilosc_zamowionych = Integer.parseInt(ilosc_zamow.getText());
                break;
            }
        }



        if (!jestDuplikat && Integer.parseInt(ilosc_zamow.getText()) <= wybranyProdukt.ilosc) {
            wybranyProdukt.ilosc_zamowionych = Integer.parseInt(ilosc_zamow.getText());
            baza.koszyk.add(wybranyProdukt);
            przejdzDoKoszyka();
        }


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
