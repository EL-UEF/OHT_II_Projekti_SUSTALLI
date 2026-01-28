package com.example.oht_ii_projekti_sustalli;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {
    //kommentti testi git
    @Override
    public void start(Stage stage) {
        TextField weeklyInput = new TextField();
        weeklyInput.setPromptText("Viikon kulutus (kg)");

        ComboBox<String> viewSelect = new ComboBox<>();
        viewSelect.getItems().addAll("Kuukausinäkymä", "Vuosinäkymä");
        viewSelect.setValue("Kuukausinäkymä");

        Button addBtn = new Button("Tallenna viikko");
        Button openChartBtn = new Button("Avaa kaavio");

        LocalDate now = LocalDate.now();

        addBtn.setOnAction(e -> {
            try {
                double value = Double.parseDouble(weeklyInput.getText());
                com.example.oht_ii_projekti_sustalli.DataStore.addWeeklyConsumption(
                        now.getYear(),
                        now.getMonthValue(),
                        value
                );
                weeklyInput.clear();
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Syötä numero").show();
            }
        });

        openChartBtn.setOnAction(e ->
                new com.example.oht_ii_projekti_sustalli.ChartWindow(viewSelect.getValue()).show()
        );

        VBox root = new VBox(10,
                new Label("Hevostallin hiilineutraalit tuotteet"),
                weeklyInput,
                viewSelect,
                addBtn,
                openChartBtn
        );

        stage.setScene(new Scene(root, 300, 250));
        stage.setTitle("Pääikkuna");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
