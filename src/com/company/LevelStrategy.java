package com.company;

public interface LevelStrategy {
    String getName();
    String[] choosePosition(Table table, String symbol);
}
