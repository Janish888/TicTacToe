package com.janish.tictactoe.model;

public class GameState {

    private Player currentPlayer;
    private Player winner;
    private boolean gameOver;

    public GameState() {
        reset();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.opposite();
    }

    public void reset() {
        currentPlayer = Player.X;
        winner = Player.EMPTY;
        gameOver = false;
    }
}