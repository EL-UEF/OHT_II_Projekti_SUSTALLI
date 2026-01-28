package com.example.sustalli;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Tietovarasto {

    // Päivämäärä -> Päivän tiedot
    private static Map<LocalDate, PaivanTiedot> tiedot = new HashMap<>();

    public static void tallennaPaivanTiedot(LocalDate pvm, double jate, double energia, double vesi, double lanta, double kuivike) {
        tiedot.put(pvm, new PaivanTiedot(jate, energia, vesi, lanta, kuivike));
    }

    public static Map<LocalDate, PaivanTiedot> haeKaikkiTiedot() {
        return tiedot;
    }
}

class PaivanTiedot {
    double jate;
    double energia;
    double vesi;
    double lanta;
    double kuivike;

    public PaivanTiedot(double jate, double energia, double vesi, double lanta, double kuivike) {
        this.jate = jate;
        this.energia = energia;
        this.vesi = vesi;
        this.lanta = lanta;
        this.kuivike = kuivike;
    }
}
