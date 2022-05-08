package com.company;

import java.util.*;

public class HardStrategy implements LevelStrategy{
    private String huSymbol;
    private String aiSymbol;

    @Override
    public String getName() {
        return "hard";
    }

    @Override
    public String[] choosePosition(Table table, String symbol) {
        huSymbol = getOppositeSymbol(symbol);
        aiSymbol = symbol;
        Map<String, String> result = minimax(table.getTable(), aiSymbol);
        return result.get("position").split("-");
    }

    private String getOppositeSymbol(String symbol) {
        if (symbol.equals("X")) return "O";
        else return "X";
    }

    private Map<String, String> minimax(String[][] board, String playerSymbol) {
        List<String[]> availableSpots = Table.getAllPositionBySymbol(board, " ");

        if (Table.winning(board, huSymbol)) return Map.of("score", "-10");
        else if (Table.winning(board, aiSymbol)) return Map.of("score", "10");
        else if (availableSpots.size() == 0) return Map.of("score", "0");

        List<Map<String, String>> tmpMoves = new ArrayList<>();
        for (String[] pos: availableSpots) {
            Map<String, String> move = new HashMap<>();
            move.put("position", pos[0] + "-" + pos[1]);

            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            board[x][y] = playerSymbol;

            Map<String, String> result = minimax(board, getOppositeSymbol(playerSymbol));
            move.put("score", result.get("score"));

            board[x][y] = " ";
            tmpMoves.add(move);
        }

        int bestMove = 0;
        int bestScore = playerSymbol.equals(aiSymbol) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < tmpMoves.size(); i++) {
            int score = Integer.parseInt(tmpMoves.get(i).get("score"));
            if (
                    (playerSymbol.equals(aiSymbol) && score > bestScore) ||
                            (playerSymbol.equals(huSymbol) && score < bestScore)
            ) {
                bestScore = score;
                bestMove = i;
            }
        }

        return tmpMoves.get(bestMove);
    }
}
