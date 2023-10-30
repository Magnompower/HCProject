package HarryCotterProject;

import java.io.File;
import java.util.Scanner;

//TODO sæt metoder i egne klasser frem for MAIN
public class Main {

    Scanner scanner = new Scanner(System.in);
    File regnskabFil = new File("src\\HarryCotterProject\\Regnskab.txt");

    int valg = -1;
    boolean betalt;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        do {
            menuStart.vaelgFraStartMenu();
        } while (!(valg == 0));
// TODO Hvis man vælger numre uden kode går den til hovedmenu - Det skal den kun ved nr 9.
    }
    // TODO Sikre text tilføjes til fil.
    //TODO Oprette objekter i stedet for
    //TODO Tilføje til fil - aftaleID og betalt: T/F


}
