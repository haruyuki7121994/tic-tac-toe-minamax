package com.company;

import java.util.Scanner;

abstract class Player {
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    abstract void play(Scanner scanner, Table table);
}
