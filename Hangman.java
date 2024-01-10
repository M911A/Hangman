import java.util.Scanner;

class Hangman {

    private char[] buchstaben;
    private String[] length;
    private boolean[] guessed; 
    private int wrongGuesses = 0;
    private final int maxWrongGuesses = 6; // hier fehler einstallbar

    public String RESET = "\u001B[0m";
    public String GREEN = "\u001B[32m";
    public String RED = "\u001B[31m";
    public String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.wortEingabe();
        hangman.vorhandenAusgabe();
        hangman.vergleich();
    }

    private void wortEingabe() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(BOLD + "Bitte geben Sie Text ein:" + RESET);
        String eingabe = scanner.nextLine().toLowerCase();//toLowerCase macht alle Buchstaben kleinen 
        buchstaben = eingabe.toCharArray();
        length = new String[buchstaben.length];
        guessed = new boolean[buchstaben.length]; // alle Werte auf false (standartmäßig)
        for (int i = 0; i < buchstaben.length; i++) {
            length[i] = "_ ";
            if (buchstaben[i] == ' ') { 
                length[i] = "  ";
                guessed[i] = true;
            }
        }
    }

    private void vorhandenAusgabe() {
        for (String letter : length) {
            System.out.print(letter);
        }
        System.out.println();
    }

    private void vergleich() {
        Scanner scanner = new Scanner(System.in);
        while (wrongGuesses < maxWrongGuesses && !allGuessed()) {
            System.out.println(BOLD + "Bitte geben Sie einen Buchstaben ein:" + RESET);
            char toCheck = scanner.nextLine().toLowerCase().charAt(0); //charAt(0) erster Buchstabe an index 0

            boolean found = false;
            for (int i = 0; i < buchstaben.length; i++) { 
                if (toCheck == buchstaben[i] && !guessed[i]) {
                    length[i] = toCheck + " ";
                    guessed[i] = true;
                    found = true;
                }
            }

            if (found) {
                System.out.println(GREEN + "Der Buchstabe ist im Wort enthalten!" + RESET);
            } else {
                System.out.println(RED + "Der Buchstabe ist nicht im Wort enthalten." + RESET);
                wrongGuesses++;
            }

            vorhandenAusgabe();
        }
        scanner.close();
        if (allGuessed()) {
            System.out.println(GREEN + BOLD + "Herzlichen Glückwunsch, Sie haben das Wort erraten!" + RESET);
        } else {
            System.out.println(RED + "Das Wort war: " + String.valueOf(buchstaben) + RESET);
            System.out.println(RED + BOLD + "Schade, Sie haben zu viele Fehler gemacht!" + RESET);
        }
    }

    private boolean allGuessed() { 
        for (boolean g : guessed) {
            if (!g) {
                return false;
            }
        }
        return true;
    }
}
