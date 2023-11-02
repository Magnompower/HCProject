package HarryCotterProject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Aftale {

    String kundeNavn;
    int kundeTlfNr;
    private int pris;
    LocalDate dato;
    LocalTime tidspunkt;
    LocalDateTime datoOgTid;
    private boolean betalt;
    Kunde kunde;


    public Aftale(Kunde kunde, int pris, LocalDate dato, LocalTime tidspunkt) {
        this.pris = pris;
        this.kunde = kunde;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.datoOgTid = LocalDateTime.of(dato, tidspunkt);
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