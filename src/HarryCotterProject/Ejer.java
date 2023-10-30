package HarryCotterProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ejer extends Personale {
    File regnskabFil = new File("src\\HarryCotterProject\\Regnskab.txt");
    Scanner filescanner=null;
    Scanner scanner = new Scanner(System.in);

    public Ejer(String navn, int id) {
        super(navn, id);
    }
    public void visRegnskab() {
        System.out.println("Dette er regnskabet:\n");
        try {
            filescanner = new Scanner(regnskabFil);
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

    public void vaelgDatoRegnskab() {
        System.out.println("Indtast dato for regnskabet du vil se([DD-MM-ÅÅÅÅ]):");
        String datoRegnskab = scanner.nextLine();
    }
}
