package com.example.sklep;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class BazaDanych{
    String selectLogin = "SELECT * FROM login";

    String selectProdukt = "SELECT * FROM produkt";
    String selectKoszyk = "SELECT * FROM koszyk";
    static String x;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sklep";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private Connection connection;
    private ArrayList<Uzytkownik> uzytkownicy = new ArrayList<>();
    public ArrayList<Produkt> produkty = new ArrayList<>();
    static public ArrayList<Produkt> koszyk = new ArrayList<>();
    static public int y =5;
    public static Uzytkownik zalogowany = new Uzytkownik();
    public ArrayList<Integer> zamowienia_id = new ArrayList<Integer>();
    int getY(){
        return y;
    }
    int koszyk_id = 1;



    public BazaDanych() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            try {
                PreparedStatement statement = connection.prepareStatement(selectLogin);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String login = resultSet.getString("login");
                    String haslo = resultSet.getString("haslo");
                    String pseudonim = resultSet.getString("pseudonim");
                    Uzytkownik uzytkownik = new Uzytkownik();
                    uzytkownik.setLogin(login);
                    uzytkownik.setHaslo(haslo);
                    uzytkownik.setPseudonim(pseudonim);
                    uzytkownicy.add(uzytkownik);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                openPopup();
            }
            try {
                PreparedStatement statement = connection.prepareStatement(selectKoszyk);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    koszyk_id++;
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                openPopup();
            }
            try {
                PreparedStatement statement = connection.prepareStatement(selectProdukt);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("produkt_id");
                    String nazwa = resultSet.getString("nazwa");
                    int ilosc = resultSet.getInt("ilosc");
                    String opis = resultSet.getString("opis");
                    String zdjecie = resultSet.getString("grafika");
                    int cena = resultSet.getInt("cena");
                    Produkt produkt = new Produkt();
                    produkt.id = id;
                    produkt.cena = cena;
                    produkt.ilosc = ilosc;
                    produkt.nazwa = nazwa;
                    produkt.opis = opis;
                    produkt.zdjecie = zdjecie;
                    produkty.add(produkt);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                openPopup();
            }
        }
        catch (ClassNotFoundException e) {
            openPopup();
        } catch (SQLException e) {
            openPopup();
        }
        closeConnection();
    }




    public void openPopup()  {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Warning");

        Label label = new Label("Utracono połączenie z serwerem proszę odświeżyć aplikację!");

        Button closeButton = new Button("Odśwież");
        Button closeButton2 = new Button("Zamknij");
        Platform.setImplicitExit(false);
        closeButton2.setOnAction(e -> {
            popupStage.close();
            Platform.exit();
        });

        popupStage.setOnCloseRequest(event -> {

            event.consume();
        });

        closeButton.setOnAction(e -> {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        if(connection != null && !connection.isClosed()){
                            popupStage.close();
                        }
                    } catch (SQLException ex) {

                    }
                    catch (ClassNotFoundException ex) {

                    }

                }
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton2,closeButton);
        layout.setStyle("-fx-padding: 10px;");
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 400, 250);
        popupStage.setScene(scene);
        popupStage.showAndWait();



    }
    public boolean logowanie(String login_in, String haslo_in) {
        for(Uzytkownik u : uzytkownicy){
            if(login_in.equals(u.getLogin()) && haslo_in.equals(u.getHaslo())){
                zalogowany = u;
                return true;
            }}
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            try {
                PreparedStatement statement = connection.prepareStatement(selectLogin);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String login = resultSet.getString("login");
                    String haslo = resultSet.getString("haslo");
                    String pseudonim = resultSet.getString("pseudonim");
                    Uzytkownik uzytkownik = new Uzytkownik();
                    uzytkownik.setLogin(login);
                    uzytkownik.setHaslo(haslo);
                    uzytkownik.setPseudonim(pseudonim);
                    uzytkownicy.add(uzytkownik);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                openPopup();
            }
            for(Uzytkownik u : uzytkownicy){
                if(login_in.equals(u.getLogin()) && haslo_in.equals(u.getHaslo())){
                    zalogowany = u;
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            openPopup();
        } catch (SQLException e) {
            System.out.println(e);
            openPopup();
        }
        closeConnection();
        return false;
    }
    public void wylogowany(){
        zalogowany.setLogin(null);
        zalogowany.setPseudonim(null);
        zalogowany.setHaslo(null);
        koszyk.clear();
    }
    @FXML
    public boolean rejestracja(String login_in, String haslo_in, String pseudonim_in){
        for(Uzytkownik u : uzytkownicy) {
            if (login_in.equals(u.getLogin()) || pseudonim_in.equals(u.getPseudonim())) {

                return false;
            }
        }
        try {

            if(login_in.trim().isEmpty() || haslo_in.trim().isEmpty() || pseudonim_in.trim().isEmpty()) {

            }
            else {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO `login` (`login_id`, `login`, `haslo`, `pseudonim`) VALUES (NULL, '" + login_in.trim() + "', '" + haslo_in.trim() + "', '" + pseudonim_in.trim() + "')");
                statement.executeUpdate();
                Uzytkownik nowy = new Uzytkownik();
                nowy.setHaslo(haslo_in.trim());
                nowy.setPseudonim(pseudonim_in.trim());
                nowy.setLogin(login_in.trim());
                uzytkownicy.add(nowy);
                zalogowany = nowy;
                statement.close();
                closeConnection();
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
            openPopup();
        }

        return false;
    }

    boolean zamowienie(String adres){
        if(koszyk.size()==0){
            return false;
        }
        else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                int ilosc_zamowionych = 0;
                int calkowita_cena = 0;
                for (Produkt produkt : koszyk){
                    ilosc_zamowionych += produkt.ilosc_zamowionych;
                    calkowita_cena += produkt.cena * produkt.ilosc_zamowionych;
                }

                String insert2 = "INSERT INTO `zamowienia` ( `koszyk_id`, `ilosc`, `adres`, `kupujacy`, `cena`) VALUES ( '" + koszyk_id + "', '" + ilosc_zamowionych + "', '" + adres + "', '" + zalogowany.getPseudonim() + "', '" + calkowita_cena + "');";
                PreparedStatement stmt2 = connection.prepareStatement(insert2);
                stmt2.executeUpdate();
                stmt2.close();

// Potem wstawienie do `koszyk`
                String insert = "INSERT INTO `koszyk` (`koszyk_id` , `kupujacy` , `adres` ) VALUES ('"+ koszyk_id +"', '" + zalogowany.getPseudonim() +"','"+ adres + "' );";
                PreparedStatement stmt = connection.prepareStatement(insert);
                stmt.executeUpdate();
                stmt.close();




                    for (Produkt produkt : koszyk){
                        String insert3 = "INSERT INTO `koszyk_produkty` ( `koszyk_id`, `produkt_id`) VALUES ( '" + koszyk_id + "', '" + produkt.id + "');";
                        PreparedStatement stmt3 = connection.prepareStatement(insert3);
                        stmt3.executeUpdate();
                        stmt3.close();
                        produkt.ilosc -= produkt.ilosc_zamowionych;
                        String update = "UPDATE `produkt` SET `ilosc` = '" + produkt.ilosc + "' WHERE `produkt`.`produkt_id` = " + produkt.id + ";";
                        PreparedStatement stmt4 = connection.prepareStatement(update);
                        stmt4.executeUpdate();
                        stmt4.close();

                    }





            } catch (ClassNotFoundException e) {
                System.out.println(e);
                openPopup();
                return false;
            } catch (SQLException e) {
                System.out.println(e);
                openPopup();
                return false;

            }
            koszyk.clear();
            closeConnection();
            return true;
        }
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


}