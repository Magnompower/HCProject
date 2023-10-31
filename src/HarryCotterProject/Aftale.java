package HarryCotterProject;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class Aftale {

    Kunde kunde;
    private int pris;
    private LocalDateTime dato;
    private boolean betalt;
    ArrayList<Aftale> regnskab;


    public Aftale(Kunde kunde, int pris, boolean betalt, String datoInput, String tidspunktIndput) {
        this.kunde = kunde;
        this.pris = pris;
        this.betalt = betalt;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.GERMAN);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate dato = LocalDate.parse(datoInput, dateFormatter);
        LocalTime tid = LocalTime.parse(tidspunktIndput, timeFormatter);
        //Der mangler at tage højde for hvis der ikke indtastes et gyldigt format.

        this.dato = LocalDateTime.of(dato, tid);
    }


    public Kunde getKunde(){
        return kunde;
    }

    public int getPris(){
        return pris;
    }


    public LocalDateTime getDato() {
        return dato;
    }



}


