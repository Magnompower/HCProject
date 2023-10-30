package HarryCotterProject;

public class Menu {
    private String menuOverskrift;
    private String[] menuPunkt;

    public Menu(String menuOverskrift, String[] menuPunkt) {
        this.menuOverskrift = menuOverskrift;
        this.menuPunkt = menuPunkt;
    }

    public void printMenu() {
        String printString = menuOverskrift + "\n";
        for (int i = 0; i < menuPunkt.length; i++) printString += menuPunkt[i] + "\n";
        System.out.println("\n" + printString);
    }
}