package com.janish.tictactoe.controller;

import com.janish.tictactoe.model.Difficulty;
import com.janish.tictactoe.model.GameMode;
import com.janish.tictactoe.model.Move;
import com.janish.tictactoe.model.Player;
import com.janish.tictactoe.service.GameManager;
import com.janish.tictactoe.view.GameView;

import javafx.scene.control.Button;

public class GameController {

    private final GameView view;
    private final GameManager gameManager;

    public GameController(GameView view) {

        this.view = view;
        this.gameManager = new GameManager();

        initializeBoard();
        initializeGameMode();
        initializeDifficulty();

        view.setDifficultyEnabled(gameManager.isHumanVsComputer());

        refreshBoard();
        updateStatus();
        updateScoreBoard();
    }

    private void initializeBoard() {

        Button[][] buttons = view.getBoardButtons();

        for (int row = 0; row < gameManager.getBoardSize(); row++) {
            for (int col = 0; col < gameManager.getBoardSize(); col++) {

                final int r = row;
                final int c = col;

                buttons[row][col].setOnAction(event -> handleMove(r, c));
            }
        }

        view.getPlayAgainButton().setOnAction(event -> playAgain());
        view.getResetScoresButton().setOnAction(event -> resetScores());
    }

    private void initializeGameMode() {

        gameManager.setGameMode(GameMode.HUMAN_VS_HUMAN);

        view.getHumanVsHumanButton().setOnAction(event -> {

            gameManager.setGameMode(GameMode.HUMAN_VS_HUMAN);
            view.setDifficultyEnabled(false);
            playAgain();

        });

        view.getHumanVsComputerButton().setOnAction(event -> {

            gameManager.setGameMode(GameMode.HUMAN_VS_COMPUTER);
            view.setDifficultyEnabled(true);
            playAgain();

        });
    }

    private void initializeDifficulty() {

        gameManager.setDifficulty(Difficulty.HARD);

        view.getDifficultyComboBox().setOnAction(event -> {

            Difficulty difficulty = view.getDifficultyComboBox().getValue();

            gameManager.setDifficulty(difficulty);

            playAgain();
        });
    }

    private void handleMove(int row, int col) {

        if (!gameManager.makeMove(row, col)) {
            return;
        }

        refreshBoard();

        if (!gameManager.isGameOver()
                && gameManager.isHumanVsComputer()) {

            gameManager.makeAIMove();
            refreshBoard();
        }

        updateStatus();

        if (gameManager.isGameOver()) {
            updateScoreBoard();
        }
    }

    private void playAgain() {

        gameManager.resetGame();

        for (Button[] row : view.getBoardButtons()) {
            for (Button button : row) {
                button.getStyleClass().remove("winner-cell");
            }
        }

        refreshBoard();
        updateStatus();
    }

    private void resetScores() {
        gameManager.resetScores();
        playAgain();
        updateScoreBoard();
    }

    private void refreshBoard() {

        Button[][] buttons = view.getBoardButtons();

        for (int row = 0; row < gameManager.getBoardSize(); row++) {
            for (int col = 0; col < gameManager.getBoardSize(); col++) {

                Player player = gameManager.getCell(row, col);

                Button button = buttons[row][col];

                button.getStyleClass().removeAll("x-cell", "o-cell");

                if (player == Player.X) {

                    button.setText("X");
                    button.getStyleClass().add("x-cell");
                    button.setDisable(true);

                } else if (player == Player.O) {

                    button.setText("O");
                    button.getStyleClass().add("o-cell");
                    button.setDisable(true);

                } else {

                    button.setText("");
                    button.setDisable(false);
                }
            }
        }

        if (gameManager.isGameOver()) {

            if (!gameManager.isDraw()) {
                highlightWinningLine();
            }

            disableBoard();
        }
    }

    private void disableBoard() {

        Button[][] buttons = view.getBoardButtons();

        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setDisable(true);
            }
        }
    }

    private void highlightWinningLine() {

        for (Move move : gameManager.getWinningLine()) {

            Button button =
                    view.getBoardButtons()[move.getRow()][move.getColumn()];

            if (!button.getStyleClass().contains("winner-cell")) {
                button.getStyleClass().add("winner-cell");
            }
        }
    }

    private void updateStatus() {

        if (gameManager.isGameOver()) {

            if (gameManager.isDraw()) {

                view.getStatusLabel().setText("Draw!");

            } else {

                view.getStatusLabel().setText(
                        "Winner: " + gameManager.getWinner());
            }

            return;
        }

        view.getStatusLabel().setText(
                "Current Turn: " + gameManager.getCurrentPlayer());
    }

    private void updateScoreBoard() {

        view.getXScoreLabel().setText(
                "Player X: " + gameManager.getXWins());

        view.getOScoreLabel().setText(
                "Player O: " + gameManager.getOWins());

        view.getDrawScoreLabel().setText(
                "Draws: " + gameManager.getDraws());
    }
}