package com.janish.tictactoe.model;

public class ScoreBoard {

    private int xWins;
    private int oWins;
    private int draws;

    public ScoreBoard() {
        reset();
    }

    public void recordWin(Player player) {

        if (player == Player.X) {
            xWins++;
        } else if (player == Player.O) {
            oWins++;
        }
    }

    public void recordDraw() {
        draws++;
    }

    public int getXWins() {
        return xWins;
    }

    public int getOWins() {
        return oWins;
    }

    public int getDraws() {
        return draws;
    }

    public void reset() {
        xWins = 0;
        oWins = 0;
        draws = 0;
    }
}