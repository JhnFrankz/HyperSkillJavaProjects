package org.jhnfrankz.projects.LastPencil;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String player1 = "John";
    private static final String player2 = "Jack";
    private static int numOfPencils = -1;
    private static boolean player1Turn = true;
    private static String winner = "";

    public static void main(String[] args) {

        readPencils();

        readFirstPlayer();

        printPencils();

        while (numOfPencils != 0) {
            System.out.printf("%s's turn!\n", player1Turn ? player1 : player2);
            readTurn();
        }
    }

    public static void readPencils() {
        while (true) {
            try {
                System.out.println("How many pencils would you like to use:");
                numOfPencils = Integer.parseInt(scanner.nextLine());

                if (numOfPencils < 0) {
                    throw new NumberFormatException();
                } else if (numOfPencils == 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                numOfPencils = -1;
            }
        }
    }

    public static void readFirstPlayer() {
        String firstPlayer;

        while (true) {
            System.out.printf("Who will be the first (%s, %s):\n", player1, player2);
            firstPlayer = scanner.nextLine();

            if (player1.equals(firstPlayer) || player2.equals(firstPlayer)) {
                break;
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        }

        player1Turn = player1.equals(firstPlayer);
    }

    public static void readTurn() {
        String input;

        while (true) {
            input = scanner.nextLine();

            if (isValidTakenPincels(input)) break;

            System.out.println("Possible values: '1', '2' or '3'");
        }

        int takenPencils = Integer.parseInt(input);

        if (takenPencils > numOfPencils) {
            System.out.println("Too many pencils were taken");
            readTurn();
        } else {
            numOfPencils -= takenPencils;

            player1Turn = !player1Turn;

            if (numOfPencils > 0) {
                printPencils();
            } else {
                winner = player1Turn ? player1 : player2;
                System.out.printf("%s won!", winner);
            }

        }
    }
    public static boolean isValidTakenPincels(String number) {
        return number.equals("1") || number.equals("2") || number.equals("3");
    }

    public static void printPencils() {
        System.out.printf("|".repeat(numOfPencils) + "\n");
    }
}
