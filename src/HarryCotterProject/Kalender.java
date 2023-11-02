package HarryCotterProject;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Kalender {

    private ArrayList<Aftale> aftaler = new ArrayList<>();

    private File kalenderFil = new File("src\\HarryCotterProject\\Kalender.txt");

    public void tilfojAftaleTilKalender(Aftale a) {
        int index = 0;
        while (index < aftaler.size() && a.getDatoOgTid().isAfter(aftaler.get(index).getDatoOgTid())) index++;
        aftaler.add(index, a); //fejl her?
    }


    public void gemIDokument(Aftale aftale) {
        OutputStream os = null;
        PrintStream ps = null;
        try {
            os = new FileOutputStream(kalenderFil, true);
            ps = new PrintStream(os);
            ps.println(aftale.getKunde().getKundenavn() + ", " +
                    aftale.getKunde().getKundeTlfNr() + ", " + aftale.getPris() +
                    ", " + aftale.getDato() + ", " + aftale.getTidspunkt());
        } catch (Exception e) {
            System.out.println("Der opstod en fejl. Prøv igen.");
        } // Vi kan ikke få lov til at lukke streamsne...
    }

    public void overskrivFilFraArraylist() {
        PrintStream ps = null;
        try {
            ps = new PrintStream((kalenderFil));
            for (int i = 0; i < aftaler.size(); i++) {
                ps.println(aftaler.get(i).getKunde().getKundenavn() + ", " +
                        aftaler.get(i).getKunde().getKundeTlfNr() + ", " + aftaler.get(i).getPris() +
                        ", " + aftaler.get(i).getDato() + ", " + aftaler.get(i).getTidspunkt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen");
        } catch (Exception e) {
            System.out.println("Der skete en fejl. Kontakt en udvikler.");
            ps.close();
        }
    }

    public void opdaterArraylistFraFil() {
        Scanner filescanner = null;
        PrintStream ps = null;
        try {
            aftaler.clear();
            filescanner = new Scanner(kalenderFil);
            while (filescanner.hasNextLine()) {
                String linje = filescanner.nextLine();
                Aftale aftale = opretAftaleFraString(linje);
                if (aftale != null) {
                    tilfojAftaleTilKalender(aftale);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet. Prøv igen.");
        } catch (Exception e) {
            System.out.println("Fejlmelding. Kontakt udvikler.");

        }
    }

    public ArrayList<Aftale> getAftaler() {
        return aftaler;
    }

    public Aftale opretAftaleFraString(String linje) {
        DateTimeFormatter datoFormaterer = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tidsFormaterer = DateTimeFormatter.ofPattern("HH:mm");
        String[] dele = new String[4];
        dele = linje.split(", ");
        int kundeTlfNr = Integer.parseInt(dele[1]); //
        Kunde kunde = new Kunde(dele[0], kundeTlfNr);

        int pris = Integer.parseInt(dele[2]);
        LocalDate dato = LocalDate.parse(dele[3], datoFormaterer);
        LocalTime tidspunkt = LocalTime.parse(dele[4], tidsFormaterer);
        return new Aftale(kunde, pris, dato, tidspunkt);
        // TODO OPRET SEPERAT METODE SOM PARSER!
    }
}


