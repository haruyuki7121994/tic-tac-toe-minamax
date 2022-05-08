package com.company;

import java.util.*;

public class Main {
    public static Player player1 = null;
    public static Player player2 = null;
    public static String[] params = null;
    public static Table table = new Table();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here
        PlayerFactory factory = new PlayerFactory();
        init();

        while (params == null || !params[0].equals("exit")) {
            while (params == null || params.length < 3) {
                System.out.print("Input command:");
                params = scanner.nextLine().split(" ");
                if (params[0].equals("exit")) break;
                else if (params.length < 3) System.out.println("Bad parameters!");
                else {
                    player1 = factory.createPlayer(params[1], "X");
                    player2 = factory.createPlayer(params[2], "O");

                    play();
                    init();
                }
            }
        }
    }

    public static void init() {
        params = null;
        player1 = null;
        player2 = null;
        table = new Table();
    }

    public static void play() {
        Player currentPlayer;
        table.displayTable();

        while (table.getSteps() < 9) {
            currentPlayer = table.getSteps() % 2 == 0 ? player1 : player2;
            currentPlayer.play(scanner, table);

            table.displayTable();

            if (Table.winning(table.getTable(), currentPlayer.getSymbol())) {
                System.out.println(currentPlayer.getSymbol() + " wins");
                break;
            }

            if (table.getSteps() >= 9) {
                System.out.println("Draw");
                break;
            }
        }
    }
}
