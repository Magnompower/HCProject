package HarryCotterProject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Aftale {

    String kundeNavn;
    int kundeTlfNr;
    private int pris = 0;
    private LocalDate dato;
    private LocalTime tidspunkt;
    private LocalDateTime datoOgTid;
    private String betaling;
    Kunde kunde;


    public Aftale(Kunde kunde, int pris, LocalDate dato, LocalTime tidspunkt, String betaling) {
        this.pris = pris;
        this.kunde = kunde;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.datoOgTid = LocalDateTime.of(dato, tidspunkt);
    }

    public void setBetaling(String betaling) {
        this.betaling = betaling;

    }

    public void setPris(int pris) {
        this.pris = pris;
    }


    public String getBetaling() {
        return betaling;
    }

    public void setBetalt(boolean betalt) {
        this.betalt = true;
    }

    public int getPris() {
        return pris;
    }

    public LocalDate getDato() {
        return dato;
    }

    public LocalTime getTidspunkt() {
        return tidspunkt;
    }

    public LocalDateTime getDatoOgTid() {
        return datoOgTid;
    }

    public Kunde getKunde() {
        return kunde;
    }


}