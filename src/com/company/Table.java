package com.company;

import java.util.*;

public class Table {
    private final String[][] table;
    private int steps = 0;
    private int[] coordinate = new int[2];

    public Table() {
        table = new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };
    }

    public String[][] getTable() {
        return table;
    }

    public void setCoordinates(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public int getSteps() {
        return steps;
    }

    public boolean addCoordinate(String[] coordinate, String symbol) {
        if (!validate(coordinate)) return false;

        int x = Integer.parseInt(coordinate[0]) - 1;
        int y = Integer.parseInt(coordinate[1]) - 1;

        if (table[x][y].equals(" ") && steps < 9) {
            table[x][y] = symbol;
            steps++;
            setCoordinates(new int[] {x, y});
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    public static boolean validate(String[] coordinatesStr) {
        for (String coordinate : coordinatesStr) {
            if (!coordinate.matches("\\d")) {
                System.out.println("You should enter numbers!");
                return false;
            }

            if (Integer.parseInt(coordinate) > 3 || Integer.parseInt(coordinate) < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        }

        return true;
    }

    public static List<String[]> getAllPositionBySymbol(String[][] board, String symbol) {
        List<String[]> positions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals(symbol)) positions.add(new String[] {String.valueOf(i), String.valueOf(j)});
            }
        }
        return positions;
    }

    public void displayTable() {
        System.out.println("---------");
        for (String[] strings : table) {
            System.out.print("| ");
            for (int j = 0; j < table.length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static boolean winning(String[][] board, String player) {
        return (board[0][0].equals(player) && board[0][1].equals(player) && board[0][2].equals(player)) ||
                (board[1][0].equals(player) && board[1][1].equals(player) && board[1][2].equals(player)) ||
                (board[2][0].equals(player) && board[2][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][0].equals(player) && board[1][0].equals(player) && board[2][0].equals(player)) ||
                (board[0][1].equals(player) && board[1][1].equals(player) && board[2][1].equals(player)) ||
                (board[0][2].equals(player) && board[1][2].equals(player) && board[2][2].equals(player)) ||
                (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }
}
