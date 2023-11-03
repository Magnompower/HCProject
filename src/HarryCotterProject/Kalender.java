package HarryCotterProject;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Kalender {

    private ArrayList<Aftale> aftaler = new ArrayList<>();

    private File kalenderFil = new File("src\\HarryCotterProject\\Kalender.txt");

    Scanner scanner = new Scanner(System.in);

    public void tilfojAftaleTilKalender(Aftale a) {
        int index = 0;
        while (index < aftaler.size() && a.getDatoOgTid().isAfter(aftaler.get(index).getDatoOgTid())) index++;
        aftaler.add(index, a); //fejl her?
    }

    public LocalDate indtastDato() {
        String datoFormat = "^(\\d{4}-\\d{2}-\\d{2})$";
        DateTimeFormatter datoFormaterer = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("Skriv dato([ÅÅÅÅ-MM-DD]):");
            String dato = scanner.nextLine().trim();

            if (dato.matches(datoFormat)) {
                try {
                    return LocalDate.parse(dato, datoFormaterer);
                } catch (Exception e) {
                    System.out.println("Ugyldig dato. Prøv igen.");
                }
            } else {
                System.out.println("Ugyldigt datoformat. Prøv igen");
            }
        }
    }


    public void gemIDokument(Aftale aftale) {
        OutputStream os = null;
        PrintStream ps = null;
        try {
            os = new FileOutputStream(kalenderFil, true);
            ps = new PrintStream(os);
            ps.println(aftale.getDato() + ", " + aftale.getTidspunkt() + ", " + aftale.getKunde().getKundenavn() +
                    ", " + aftale.getKunde().getKundeTlfNr() + ", " + aftale.getPris() +
                    ", " + aftale.getBetaling());
        } catch (Exception e) {
            System.out.println("Der opstod en fejl. Prøv igen.");
        } // Vi kan ikke få lov til at lukke streamsne...
    }

    public void overskrivFilFraArraylist() {
        PrintStream ps = null;
        try {
            ps = new PrintStream((kalenderFil));
            for (int i = 0; i < aftaler.size(); i++) {
                ps.println(aftaler.get(i).getDato() + ", " + aftaler.get(i).getTidspunkt() +
                        ", " + aftaler.get(i).getKunde().getKundenavn() +
                        ", " + aftaler.get(i).getKunde().getKundeTlfNr() +
                        ", " + aftaler.get(i).getPris() +
                        ", " + aftaler.get(i).getBetaling());
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
        String[] dele;
        dele = linje.split(", ");
        int kundeTlfNr = Integer.parseInt(dele[3]); //
        Kunde kunde = new Kunde(dele[2], kundeTlfNr);

        int pris = Integer.parseInt(dele[4]);
        LocalDate dato = LocalDate.parse(dele[0], datoFormaterer);
        LocalTime tidspunkt = LocalTime.parse(dele[1], tidsFormaterer);
        String betaling = dele[5];
        return new Aftale(dato, tidspunkt, kunde, pris, betaling);
        // TODO OPRET SEPERAT METODE SOM PARSER!
    }


    public void printRegskabForDato() {
        LocalDate valgtDato = indtastDato();

        for (int i = 0; i < aftaler.size(); i++) {
            if (aftaler.get(i).getDato().isEqual(valgtDato)) {
                System.out.println("Dato: " + aftaler.get(i).getDato() +
                        " Tidspunkt: " + aftaler.get(i).getTidspunkt() +
                        " Navn: " + aftaler.get(i).getKunde().getKundenavn() +
                        " Pris: " + aftaler.get(i).getPris() +
                        " Betalingsstatus: " + aftaler.get(i).getBetaling());
            }
        }
    }


    public void printHelekalender() {
        for (int i = 0; i < aftaler.size(); i++) {
            System.out.println("Aftalenummer: " + (i + 1) + ". Navn: " +
                    aftaler.get(i).getKunde().getKundenavn() + " Tlf: " +
                    aftaler.get(i).getKunde().getKundeTlfNr() + " Pris: " +
                    aftaler.get(i).getPris() + " Dato: " + aftaler.get(i).getDato() +
                    " Tidspunkt: " + aftaler.get(i).getTidspunkt());
        }
    }
}


