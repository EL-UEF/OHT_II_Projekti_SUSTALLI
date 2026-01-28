package com.example.oht_ii_projekti_sustalli;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ChartWindow extends Stage {

    private int year = java.time.Year.now().getValue();
    private int month = java.time.LocalDate.now().getMonthValue();
    private final boolean monthlyView;

    public ChartWindow(String mode) {
        this.monthlyView = mode.equals("Kuukausinäkymä");
        drawChart();
    }

    private void drawChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        if (monthlyView) {
            series.getData().add(new XYChart.Data<>(
                    "Kuukausi " + month,
                    DataStore.getMonthlyTotal(year, month)
            ));
            chart.setTitle("Kuukausikulutus " + year);
        } else {
            series.getData().add(new XYChart.Data<>(
                    "Vuosi " + year,
                    DataStore.getYearlyTotal(year)
            ));
            chart.setTitle("Vuosikulutus");
        }

        chart.getData().add(series);

        Scene scene = new Scene(chart, 500, 400);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                if (monthlyView) month++;
                else year++;
                refresh();
            }
            if (e.getCode() == KeyCode.LEFT) {
                if (monthlyView) month--;
                else year--;
                refresh();
            }
        });

        setScene(scene);
    }

    private void refresh() {
        drawChart();
    }
}
