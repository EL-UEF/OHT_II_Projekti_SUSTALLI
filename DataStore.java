package com.example.oht_ii_projekti_sustalli;

import java.util.*;

public class DataStore {

    // vuosi -> kuukausi -> viikot
    private static final Map<Integer, Map<Integer, List<Double>>> data = new HashMap<>();

    public static void addWeeklyConsumption(int year, int month, double value) {
        data
                .computeIfAbsent(year, y -> new HashMap<>())
                .computeIfAbsent(month, m -> new ArrayList<>())
                .add(value);
    }

    public static double getMonthlyTotal(int year, int month) {
        return data.getOrDefault(year, Map.of())
                .getOrDefault(month, List.of())
                .stream().mapToDouble(Double::doubleValue).sum();
    }

    public static double getYearlyTotal(int year) {
        return data.getOrDefault(year, Map.of())
                .values().stream()
                .flatMap(List::stream)
                .mapToDouble(Double::doubleValue).sum();
    }
}
