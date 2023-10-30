package HarryCotterProject;

import java.io.File;
import java.util.Scanner;

//TODO sæt metoder i egne klasser frem for MAIN
public class Main {

    Scanner scanner = new Scanner(System.in);

    int valg = -1;
    boolean betalt;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        menurunner.eksekverMenu();


//TODO Hvis man vælger numre uden kode går den til hovedmenu - Det skal den kun ved nr 9.
    }
    // TODO Sikre text tilføjes til fil.
    //TODO Oprette objekter i stedet for
    //TODO Tilføje til fil - aftaleID og betalt: T/F


}
