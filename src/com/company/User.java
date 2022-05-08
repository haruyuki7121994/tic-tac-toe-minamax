package com.company;

import java.util.Scanner;

public class User extends Player{
    @Override
    void play(Scanner scanner, Table table) {
        String[] coordinatesStr;
        do {
            System.out.print("Enter the coordinates:");
            coordinatesStr = scanner.nextLine().split(" ");
        } while (!table.addCoordinate(coordinatesStr, getSymbol()));
    }
}
