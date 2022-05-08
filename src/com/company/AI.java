package com.company;

import java.util.Scanner;

public class AI extends Player{
    private LevelStrategy level;

    public AI(String level) {
        switch (level) {
            case "easy":
                this.level = new EasyStrategy();
                break;
            case "medium":
                this.level = new MediumStrategy();
                break;
            case "hard":
                this.level = new HardStrategy();
                break;
        }
    }

    public String getLevel() {
        return level.getName();
    }

    @Override
    void play(Scanner scanner, Table table) {
        System.out.printf("Making move level \"%s\"\n", getLevel());
        String[] position = level.choosePosition(table, getSymbol());
        position[0] = String.valueOf(Integer.parseInt(position[0]) + 1);
        position[1] = String.valueOf(Integer.parseInt(position[1]) + 1);
        table.addCoordinate(position, getSymbol());
    }
}
