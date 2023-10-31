package HarryCotterProject;

import java.io.*;
import java.util.Scanner;

public class Personale extends Person {
    private int kassebeholdning = 0;
    private int betalingssum = 0;
    private Scanner filescanner = null;
    Scanner scanner = new Scanner(System.in);
    private boolean betalt;
    Kalender kalender = new Kalender();

    private File kalenderFil = new File("src\\HarryCotterProject\\Kalender.txt"); //skal ikke være her


    public Personale(String navn, int id) {
        super(navn, id);
    }


    public void opretAftale() {
        System.out.println("Skriv kundens navn:");
        String kundeNavn = scanner.nextLine();

        System.out.println("Skriv kundens telefon nr:");
        int kundeTlfNr = scanner.nextInt();

        Kunde kunde = new Kunde(kundeNavn, kundeTlfNr);

        System.out.println("Skriv pris:");
        int pris = scanner.nextInt();
        scanner.nextLine(); //SCANNERBUG

        System.out.println("Skriv dato([DD-MM-ÅÅÅÅ]):");
        String dato = scanner.nextLine();
        System.out.println("Skriv tidspunkt( [TT:MM]):");
        String tidspunkt = scanner.nextLine();

        Aftale aftale = new Aftale(kunde, pris, betalt, dato, tidspunkt);

        kalender.tilfojAftaleTilKalender(aftale);

    }




    public void sletAftale() {
        System.out.println("Vælg hvilken linje du vil slette.");
        System.out.println(kalenderFil);

        int idSlettes = -1;
        int linje = scanner.nextInt();

        StringBuilder indhold = new StringBuilder();
        try {
            PrintWriter tekstTilFil = new PrintWriter(new FileWriter(kalenderFil));

            BufferedReader tekst = new BufferedReader(new FileReader(kalenderFil));
            while (filescanner.hasNextLine()) {
                if (linje != idSlettes) {
//                  tekstTilFil.println(linje);
                }
                linje++;

            }

            filescanner = new Scanner(kalenderFil);
            System.out.println(kalenderFil);
        } catch (Exception e) {
            System.out.println("Fejl opstod. Find en mur.");
        }
    }

    public void visKalender() {
        System.out.println("Dette er kalenderen:");
        try {
            filescanner = new Scanner(kalenderFil);
            while (filescanner.hasNextLine()) {
                String s = filescanner.nextLine();
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der opstod en fejl. Kontakt en udvikler.");
        }
        filescanner.close();
    }



    public void modtagBetaling() {
        System.out.println("Vælg aftale:");
        kassebeholdning = kassebeholdning + betalingssum;
        betalt = true;
    }
}
