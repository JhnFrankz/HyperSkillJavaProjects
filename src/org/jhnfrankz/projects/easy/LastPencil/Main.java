package org.jhnfrankz.projects.easy.LastPencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String player1 = "John";
    private static final String bot1 = "Jack";
    private static int numOfPencils = -1;
    private static boolean playerTurn = true;

    public static void main(String[] args) {

        readPencils();
        readFirstPlayer();
        printPencils();

        while (numOfPencils != 0) {
            System.out.printf("%s's turn%s\n", playerTurn ? player1 : bot1, playerTurn ? "!" : ":");
            if (playerTurn) {
                readPlayerTurn();
            } else {
                turnOfBot();
            }

            if (numOfPencils > 0) {
                printPencils();
            } else {
                String winner = playerTurn ? player1 : bot1;
                System.out.printf("%s won!", winner);
            }
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
            System.out.printf("Who will be the first (%s, %s):\n", player1, bot1);
            firstPlayer = scanner.nextLine();

            if (player1.equals(firstPlayer) || bot1.equals(firstPlayer)) {
                break;
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        }

        playerTurn = player1.equals(firstPlayer);
    }

    public static void readPlayerTurn() {
        String input;

        while (true) {
            input = scanner.nextLine();

            if (isValidNumberOfPencils(input)) break;

            System.out.println("Possible values: '1', '2' or '3'");
        }

        int takenPencils = Integer.parseInt(input);

        takePencils(takenPencils);
    }

    public static void takePencils(int takenPencils) {
        if (isValidTakenPencils(takenPencils)) {
            numOfPencils -= takenPencils;

            playerTurn = !playerTurn;
        } else {
            System.out.println("Too many pencils were taken");
            readPlayerTurn();
        }
    }

    public static void turnOfBot() {
        int takePencils;

        if (isWinnerStrategy()) {
            takePencils = takePencilsAsWinnerStrategy();
            //numOfPencils -= takePencilsAsWinnerStrategy();
        } else {
            do {
                takePencils = getRandomNumberOfPencils();
            } while (!isValidTakenPencils(takePencils));
        }

        takePencils(takePencils);
        System.out.println(takePencils);
    }

    public static int takePencilsAsWinnerStrategy() {
        return switch (numOfPencils % 4) {
            case 0 -> 3;
            case 3 -> 2;
            case 2 -> 1;
            default -> 0;
        };
    }

    public static boolean isWinnerStrategy() {
        return !(numOfPencils % 4 == 1); // if is not numOfPencils is 1,5,9,13,17... is losing
    }

    public static boolean isValidTakenPencils(int takenPencils) {
        return numOfPencils >= takenPencils;
    }

    public static int getRandomNumberOfPencils() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    public static boolean isValidNumberOfPencils(String number) {
        return number.equals("1") || number.equals("2") || number.equals("3");
    }

    public static void printPencils() {
        System.out.printf("|".repeat(numOfPencils) + "\n");
    }
}
