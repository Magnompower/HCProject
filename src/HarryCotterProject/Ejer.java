package HarryCotterProject;

import java.io.File;
import java.util.Scanner;

public class Ejer extends Personale {
    Scanner filescanner = null;
    Scanner scanner = new Scanner(System.in);

    public Ejer(String navn, int id) {
        super(navn, id);
    }

    public void visHeleRegnskab(Kalender kalender) {

        System.out.println("Dette er regnskabet:\n");
        try {

            for (int i = 0; i < kalender.getAftaler().size(); i++) {
                System.out.println("Aftalenummer: " + (i + 1) + ". Navn: " +
                        kalender.getAftaler().get(i).getKunde().getKundenavn() + " Pris: " +
                        kalender.getAftaler().get(i).getPris() + " Dato: " + kalender.getAftaler().get(i).getDato() +
                        " Tidspunkt: " + kalender.getAftaler().get(i).getTidspunkt() +
                        " Betalingsstatus: " + kalender.getAftaler().get(i).getBetaling());
            }
        } catch (
                Exception e) {
            System.out.println("Der opstod en fejl. Kontakt en udvikler.");
        }
    }

    public void vaelgDatoRegnskab() {
        System.out.println("Indtast dato for regnskabet du vil se([DD-MM-ÅÅÅÅ]):");
        String datoRegnskab = scanner.nextLine();
    }
}
