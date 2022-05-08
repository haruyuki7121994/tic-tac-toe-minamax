package com.company;

import java.util.List;
import java.util.Random;

public class MediumStrategy implements LevelStrategy{
    @Override
    public String getName() {
        return "medium";
    }

    @Override
    public String[] choosePosition(Table table, String symbol) {
        String[] position = getPosition(table.getTable(), symbol, getOppositeSymbol(symbol));
        if (position != null) {
            return position;
        }

        position = getPosition(table.getTable(), getOppositeSymbol(symbol), symbol);
        if (position != null) {
            return position;
        }

        List<String[]> emptyPositions = Table.getAllPositionBySymbol(table.getTable(), " ");
        Random rd = new Random();
        int idx = rd.nextInt(emptyPositions.size());

        return emptyPositions.get(idx);
    }

    private String getOppositeSymbol(String symbol) {
        if (symbol.equals("X")) return "O";
        else return "X";
    }

    public String[] getPosition(String[][] table, String symbol, String oppositeSymbol) {
        String[] position = new String[2];

        int tmp;
        int cnt;
        boolean found;
        for (int y = 0; y < table.length; y++) {
            tmp = 0;
            cnt = 0;
            position = new String[2];
            found = false;
            while (tmp <= 2) {
                if (table[tmp][y].equals(oppositeSymbol)) break;
                if (table[tmp][y].equals(symbol)) cnt++;
                if (table[tmp][y].equals(" ")) {
                    position[0] = String.valueOf(tmp);
                    position[1] = String.valueOf(y);
                    found = true;
                }
                tmp++;
            }
            if (found && cnt >= 2) return position;

            tmp = 0;
            cnt = 0;
            position = new String[2];
            found = false;
            while (tmp <= 2) {
                if (table[y][tmp].equals(oppositeSymbol)) break;
                if (table[y][tmp].equals(symbol)) {
                    cnt++;
                }
                if (table[y][tmp].equals(" ")) {
                    position[0] = String.valueOf(y);
                    position[1] = String.valueOf(tmp);
                    found = true;
                }
                tmp++;
            }
            if (found && cnt >= 2) return position;
        }

        tmp = 0;
        cnt = 0;
        found = false;
        while (tmp <= 2) {
            if (table[tmp][tmp].equals(oppositeSymbol)) break;
            if (table[tmp][tmp].equals(symbol)) cnt++;
            if (table[tmp][tmp].equals(" ")) {
                position[0] = String.valueOf(tmp);
                position[1] = String.valueOf(tmp);
                found = true;
            }
            tmp++;
        }
        if (found && cnt >= 2) return position;

        int x = 0;
        int y = 2;
        cnt = 0;
        found = false;
        while (x <= 2 || y >= 0) {
            if (table[x][y].equals(oppositeSymbol)) break;
            if (table[x][y].equals(symbol)) cnt++;
            if (table[x][y].equals(" ")) {
                position[0] = String.valueOf(x);
                position[1] = String.valueOf(y);
                found = true;
            }
            x++;
            y--;
        }
        if (found && cnt >= 2) return position;

        return null;
    }
}
