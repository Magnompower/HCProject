package HarryCotterProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.PrintStream;

public class Kalender {

    private ArrayList<Aftale> aftaler;
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
            ps = new PrintStream(new FileOutputStream(kalenderFil, true));
            ps.println("Navn: " + a.getKunde().getKundenavn() + " Tlf: " + a.getKunde().getKundeTlfNr() +
                    " Pris: " + a.getPris() + ",- Tidspunkt: " + a.getDato());
            System.out.println("Aftalen er gemt og ser sådan ud:");
            System.out.println("Navn: " + a.getKunde() + " Pris: " + a.getPris() + ",- Tidspunkt: ");// Er det her nødvendigt?

        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der skete en fejl. Kontakt en udvikler.");
        }
        ps.close();
    }


}
