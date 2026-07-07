package com.janish.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int SIZE = 3;
    private final Player[][] board;

    public Board() {
        board = new Player[SIZE][SIZE];
        reset();
    }

    /**
     * Copy constructor.
     */
    public Board(Board other) {

        board = new Player[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = other.board[row][col];
            }
        }
    }

    public Player getCell(int row, int column) {

        if (!isValidPosition(row, column)) {
            throw new IllegalArgumentException("Invalid board position.");
        }

        return board[row][column];
    }

    public boolean setCell(int row, int column, Player player) {

        if (!isValidPosition(row, column)) {
            return false;
        }

        if (!isCellEmpty(row, column)) {
            return false;
        }

        board[row][column] = player;
        return true;
    }

    public boolean makeMove(Move move, Player player) {
        return setCell(move.getRow(), move.getColumn(), player);
    }

    public void clearCell(int row, int column) {
        board[row][column] = Player.EMPTY;
    }

    public void clearCell(Move move) {
        clearCell(move.getRow(), move.getColumn());
    }

    public boolean isCellEmpty(int row, int column) {

        if (!isValidPosition(row, column)) {
            return false;
        }

        return board[row][column] == Player.EMPTY;
    }

    public boolean isValidPosition(int row, int column) {

        return row >= 0 && row < SIZE
                && column >= 0 && column < SIZE;
    }

    public boolean hasWinner() {

        return getWinner() != Player.EMPTY;
    }

    public Player getWinner() {

        for (int row = 0; row < SIZE; row++) {

            Player first = board[row][0];

            if (first != Player.EMPTY
                    && first == board[row][1]
                    && first == board[row][2]) {
                return first;
            }
        }

        for (int col = 0; col < SIZE; col++) {

            Player first = board[0][col];

            if (first != Player.EMPTY
                    && first == board[1][col]
                    && first == board[2][col]) {
                return first;
            }
        }

        if (board[0][0] != Player.EMPTY
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != Player.EMPTY
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return Player.EMPTY;
    }

    public List<Move> getWinningLine() {
        List<Move> winningLine = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {
            Player first = board[row][0];
            if (first != Player.EMPTY
                    && first == board[row][1]
                    && first == board[row][2]) {
                winningLine.add(new Move(row, 0));
                winningLine.add(new Move(row, 1));
                winningLine.add(new Move(row, 2));
                return winningLine;
            }
        }

        for (int col = 0; col < SIZE; col++) {
            Player first = board[0][col];
            if (first != Player.EMPTY
                    && first == board[1][col]
                    && first == board[2][col]) {
                winningLine.add(new Move(0, col));
                winningLine.add(new Move(1, col));
                winningLine.add(new Move(2, col));
                return winningLine;
            }
        }

        if (board[0][0] != Player.EMPTY
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {
            winningLine.add(new Move(0, 0));
            winningLine.add(new Move(1, 1));
            winningLine.add(new Move(2, 2));
            return winningLine;
        }

        if (board[0][2] != Player.EMPTY
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {
            winningLine.add(new Move(0, 2));
            winningLine.add(new Move(1, 1));
            winningLine.add(new Move(2, 0));
            return winningLine;
        }

        return winningLine;
    }

    public boolean isFull() {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == Player.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == Player.EMPTY) {
                    moves.add(new Move(row, col));
                }
            }
        }
        return moves;
    }

    public void reset() {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = Player.EMPTY;
            }
        }
    }

    public int getSize() {
        return SIZE;
    }
}