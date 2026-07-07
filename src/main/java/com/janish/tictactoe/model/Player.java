package com.janish.tictactoe.model;

public enum Player {

    X,
    O,
    EMPTY;

    public Player opposite() {
        return switch (this) {
            case X -> O;
            case O -> X;
            default -> EMPTY;
        };
    }
}