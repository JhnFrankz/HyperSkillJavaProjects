package org.jhnfrankz.projects.LastPencil;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String player1 = "John";
    private static final String player2 = "Jack";
    private static int numOfPencils = 0;
    private static boolean player1Turn = true;

    public static void main(String[] args) {
        System.out.println("How many pencils would you like to use: ");
        numOfPencils = Integer.parseInt(scanner.nextLine());

        System.out.printf("Who will be the first (%s, %s):\n", player1, player2);
        String firstPlayer = scanner.nextLine();

        player1Turn = player1.equals(firstPlayer);

        printPencils();
        System.out.printf("%s is going first!\n", player1Turn ? player1 : player2);
    }

    public static void printPencils() {
        System.out.printf("|".repeat(numOfPencils) + "\n");
    }
}
