package HarryCotterProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Personale extends Person {
    int kassebeholdning = 0;
    int betalingssum = 0;

    Scanner filescanner = null;
    Scanner scanner = new Scanner(System.in);
    File kalenderFil = new File("src\\HarryCotterProject\\Kalender.txt");
    boolean betalt;

    public Personale(String navn, int id) {
        super(navn, id);
    }

    public void opretAftale() {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(kalenderFil, true));
            System.out.println("Skriv kundeID:");
            int kundeID = scanner.nextInt();

            System.out.println("Skriv pris:");
            int pris = scanner.nextInt();
            scanner.nextLine(); //SCANNERBUG

            System.out.println("Skriv dato og tidspunkt([DD-MM-ÅÅÅÅ] [TT:MM]):");
            String tidspunkt = scanner.nextLine();

            //TODO Vi skal have seperet tidspunktet fra datoen, så det ikke står samlet fx 2 variabler.

            Aftale aftale = new Aftale(kundeID, pris, tidspunkt, betalt);
            ps.println("KundeID: " + kundeID + " Pris: " + pris + ",- Dato: " + tidspunkt);
            System.out.println("Aftalen er gemt og ser sådan ud:");
            System.out.println("KundeID: " + kundeID + " Pris: " + pris + ",- Dato: " + tidspunkt);


        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der skete en fejl. Kontakt en udvikler.");
        }
        ps.close();
        vaelgMenu();
    }


    public void sletAftale() {
        try {
            Scanner fileScanner = new Scanner(kalenderFil);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        vaelgMenu();
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
        vaelgMenu();
    }

    public void modtagBetaling() {
        System.out.println("Vælg aftale:");
        kassebeholdning = kassebeholdning + betalingssum;
        betalt = true;
        vaelgMenu();
    }

}
