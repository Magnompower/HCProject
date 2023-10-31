package HarryCotterProject;

import java.util.ArrayList;
import java.util.Random;

public class Aftale {
    int aftaleID;
    int kundeID; // ARRAYLIST? Forestiller mig det giver mere mening at lave en arraylist med Kunde objekter
    int pris;
    int dag;
    int maaned;
    int aar;
    String dato;
    String tidspunkt;
    boolean betalt;
    //    service
    ArrayList<Aftale> regnskab;


    public Aftale(int kundeID, int pris, String dato, String tidspunkt, boolean betalt) {
        this.kundeID = kundeID;
        this.pris = pris;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
//        aftaleID = forløkke! TODO
        this.betalt = betalt;
    }
}
