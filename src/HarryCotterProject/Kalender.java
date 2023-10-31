package HarryCotterProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Kalender {

    private ArrayList<Aftale> aftaler = new ArrayList<>();
    private ArrayList<String> dele = new ArrayList<>();
    private File kalenderFil = new File("src\\HarryCotterProject\\Kalender.txt");

    public void tilfojAftaleTilKalender(Aftale a) {
        int index = 0;
        while (index < aftaler.size() && a.getDato().isAfter(aftaler.get(index).getDato())) index++;
        aftaler.add(index, a);
        gemIDokument(a);

    }


    public void gemIDokument(Aftale a) {
        PrintStream ps = null;
        try {
            ps = new PrintStream((kalenderFil));
            for (int i = 0; i < aftaler.size(); i++) {
                ps.println("Navn: " + aftaler.get(i).getKunde().getKundenavn() + " Tlf: " + aftaler.get(i).getKunde().getKundeTlfNr() + " Pris: " + aftaler.get(i).getPris() + ",- Tidspunkt: " + aftaler.get(i).getDato());
            }
            System.out.println("\nDin aftale ser sådan ud:\n");
            System.out.println("Navn: " + a.getKunde().getKundenavn() + " Tlf: " + a.getKunde().getKundeTlfNr() + " Pris: " + a.getPris() + ",- Tidspunkt: " + a.getDato());
            //TODO ÆNDRE A!!!!
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der skete en fejl. Kontakt en udvikler.");

            ps.close();
        }

    }

    public File getKalenderFil() {
        return kalenderFil;
    }

    public void kopierFraDokumentTilArray() {
        Scanner filescanner = null;

        try {
            filescanner = new Scanner(kalenderFil);
            aftaler.clear();
            while (filescanner.hasNextLine()) {
                String linje = filescanner.nextLine();
                Aftale aftale = opretAftaleFraString(linje);
                if (aftale != null) {
                    aftaler.add(aftale); // TODO HUSKE RÆKKKEFØLGE
                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen.");
        } catch (Exception e) {
            System.out.println("Fejlmelding. Kontakt udvikler.");
        }
    }

    public Aftale opretAftaleFraString(String linje) {
        ArrayList dele = linje.split(": ");
        Kunde kunde = new Kunde(dele.get(1));
        Aftale aftale = new Aftale();
        return null;
    }
}
