package com.example.sklep;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class KoszykController implements Initializable {

    BazaDanych baza = new BazaDanych();

    @FXML
    private Label pseudo = new Label();
    @FXML
    private Button wyloguj = new Button();
    @FXML
    private Button ProduktyButton = new Button();
    @FXML
    private Label cena_razem = new Label();
    @FXML
    private Label pseudonim_zam = new Label();
    @FXML
    private Label cena_zam = new Label();
    @FXML
    private GridPane gridPane = new GridPane();
    @FXML
    private ScrollPane scrollPane = new ScrollPane();
    @FXML
    private SplitPane splitPane = new SplitPane();
    private String previousCena2 = cena_razem.getText();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicInteger x = new AtomicInteger();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    x.getAndIncrement();
                    pseudo.setText(baza.zalogowany.getPseudonim());
                    String currentCena2 = cena_razem.getText();
                    pseudonim_zam.setText(baza.zalogowany.getPseudonim());
                    if(!currentCena2.equals(previousCena2)) {
                        updateKoszyk();
                        previousCena2=currentCena2;
                    }
                    cena_zam.setText(baza.x+ ",00 zł");
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void updateKoszyk() {
        int row = 0;
        int column = 0;
        int cena_ = 0;
        for (Produkt produkt : baza.koszyk) {
            ImageView imageView = new ImageView(getClass().getResource("/screeny_sklep/" + produkt.zdjecie).toExternalForm());
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            Label title = new Label(produkt.nazwa);
            Label cost = new Label(String.valueOf(produkt.cena));
            Label value = new Label(String.valueOf("Ilość: " + produkt.ilosc_zamowionych));
            Button button = new Button("Usuń");
            button.setOnAction(event -> {
                baza.koszyk.remove(produkt);
                gridPane.getChildren().clear();
                updateKoszyk();
            });
            value.setWrapText(true);
            cost.setWrapText(true);
            title.setWrapText(true);

            button.setId(String.valueOf(row));

            gridPane.add(imageView, column++, row);
            gridPane.add(title, column++, row);
            gridPane.add(cost, column++, row);
            gridPane.add(value, column++, row);
            gridPane.add(button, column++, row);


            GridPane.setHalignment(imageView, HPos.CENTER);
            GridPane.setValignment(imageView, VPos.CENTER);

            GridPane.setHalignment(title, HPos.CENTER);
            GridPane.setValignment(title, VPos.CENTER);

            GridPane.setHalignment(cost, HPos.CENTER);
            GridPane.setValignment(cost, VPos.CENTER);

            GridPane.setHalignment(value, HPos.CENTER);
            GridPane.setValignment(value, VPos.CENTER);

            GridPane.setHalignment(button, HPos.CENTER);
            GridPane.setValignment(button, VPos.CENTER);

            row++;
            column = 0;
            cena_ += produkt.cena * produkt.ilosc_zamowionych;
        }

        scrollPane.setContent(splitPane);
        cena_razem.setText(cena_ + ",00 zł");
        baza.x = String.valueOf(cena_);

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
    public void Wyloguj() throws IOException{

        baza.wylogowany();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) wyloguj.getScene().getWindow();
        stage.setScene(scene);

    }
    @FXML
    public void przejdzDoZamowienia() throws IOException {
        if (baza.koszyk.isEmpty()) {

        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zamowienie.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ProduktyButton.getScene().getWindow();
            stage.setScene(scene);
        }
    }
}
