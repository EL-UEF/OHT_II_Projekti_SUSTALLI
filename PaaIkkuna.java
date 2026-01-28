package com.example.sustalli;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PaaIkkuna extends Application {

    @Override
    public void start(Stage stage) {

        DatePicker paivamaaraValinta = new DatePicker(LocalDate.now());

        TextField jateKentta = new TextField();
        jateKentta.setPromptText("Jäte (kg)");

        TextField energiaKentta = new TextField();
        energiaKentta.setPromptText("Energia (kWh)");

        TextField vesiKentta = new TextField();
        vesiKentta.setPromptText("Vesi (l)");

        TextField lantaKentta = new TextField();
        lantaKentta.setPromptText("Lanta (kg)");

        TextField kuivikeKentta = new TextField();
        kuivikeKentta.setPromptText("Kuivike (kg)");

        ComboBox<String> nayttoValinta = new ComboBox<>();
        nayttoValinta.getItems().addAll("Jäte", "Energia", "Vesi", "Lanta", "Kuivike");
        nayttoValinta.setValue("Jäte");

        Button tallennaBtn = new Button("Tallenna tiedot");
        Button avaaKaavioBtn = new Button("Avaa kaavio");

        tallennaBtn.setOnAction(e -> {
            try {
                LocalDate pvm = paivamaaraValinta.getValue();

                double jate = Double.parseDouble(jateKentta.getText());
                double energia = Double.parseDouble(energiaKentta.getText());
                double vesi = Double.parseDouble(vesiKentta.getText());
                double lanta = Double.parseDouble(lantaKentta.getText());
                double kuivike = Double.parseDouble(kuivikeKentta.getText());

                Tietovarasto.tallennaPaivanTiedot(pvm, jate, energia, vesi, lanta, kuivike);

                jateKentta.clear();
                energiaKentta.clear();
                vesiKentta.clear();
                lantaKentta.clear();
                kuivikeKentta.clear();

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Syötä vain numeroita").show();
            }
        });

        avaaKaavioBtn.setOnAction(e -> {
            KaavioIkkuna ikkuna = new KaavioIkkuna(nayttoValinta.getValue());
            ikkuna.show();
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(
                new Label("SUSTALLI – Päivittäinen ympäristötietojen syöttö"),
                paivamaaraValinta,
                jateKentta,
                energiaKentta,
                vesiKentta,
                lantaKentta,
                kuivikeKentta,
                nayttoValinta,
                tallennaBtn,
                avaaKaavioBtn
        );

        stage.setScene(new Scene(root, 350, 450));
        stage.setTitle("SUSTALLI – Pääikkuna");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
