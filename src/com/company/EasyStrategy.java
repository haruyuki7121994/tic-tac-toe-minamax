package com.company;

import java.util.List;
import java.util.Random;

public class EasyStrategy implements LevelStrategy{
    @Override
    public String getName() {
        return "easy";
    }

    @Override
    public String[] choosePosition(Table table, String symbol) {
        List<String[]> emptyPositions = Table.getAllPositionBySymbol(table.getTable(), " ");
        Random rd = new Random();
        int idx = rd.nextInt(emptyPositions.size());
        return emptyPositions.get(idx);
    }
}
