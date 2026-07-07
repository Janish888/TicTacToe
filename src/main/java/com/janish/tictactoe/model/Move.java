package com.janish.tictactoe.model;

public class Move {

    private final int row;
    private final int column;
    private int score;

    public Move(int row, int column) {
        this(row, column, 0);
    }

    public Move(int row, int column, int score) {
        this.row = row;
        this.column = column;
        this.score = score;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}