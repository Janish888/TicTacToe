package com.janish.tictactoe.service;

import com.janish.tictactoe.model.Board;
import com.janish.tictactoe.model.GameState;
import com.janish.tictactoe.model.Player;
import com.janish.tictactoe.model.ScoreBoard;
import com.janish.tictactoe.ai.MinimaxAI;
import java.util.Random;
import com.janish.tictactoe.ai.RandomAI;
import com.janish.tictactoe.model.GameMode;
import com.janish.tictactoe.model.Difficulty;
import com.janish.tictactoe.model.Move;
import java.util.List;
import com.janish.tictactoe.model.Move;

public class GameManager {

    private final Board board;
    private final GameState gameState;
    private final ScoreBoard scoreBoard;
    private final MinimaxAI minimaxAI;
    private final RandomAI randomAI;
    private final Random random;
    private Difficulty difficulty;
    private GameMode gameMode;

    public GameManager() {
        board = new Board();
        gameState = new GameState();
        scoreBoard = new ScoreBoard();
        minimaxAI = new MinimaxAI(Player.O);
        randomAI = new RandomAI();
        random = new Random();
        difficulty = Difficulty.HARD;
        gameMode = GameMode.HUMAN_VS_HUMAN;
    }

    public boolean makeMove(int row, int col) {

        if (gameState.isGameOver()) {
            return false;
        }

        Player currentPlayer = gameState.getCurrentPlayer();

        boolean moveSuccessful = board.setCell(row, col, currentPlayer);

        if (!moveSuccessful) {
            return false;
        }

        if (board.hasWinner()) {

            gameState.setWinner(board.getWinner());
            gameState.setGameOver(true);

            scoreBoard.recordWin(gameState.getWinner());

            return true;
        }

        if (board.isFull()) {

            gameState.setGameOver(true);

            scoreBoard.recordDraw();

            return true;
        }

        gameState.switchPlayer();

        return true;
    }

    public boolean makeAIMove() {
        if (!isHumanVsComputer()) {
            return false;
        }
        if (gameState.isGameOver()) {
            return false;
        }
        Move move;
        switch (difficulty) {
            case EASY -> move = randomAI.chooseMove(getBoardCopy());
            case MEDIUM -> {
                if (random.nextDouble() < 0.7) {
                    move = minimaxAI.chooseMove(getBoardCopy());
                } else {
                    move = randomAI.chooseMove(getBoardCopy());
                }
            }
            case HARD -> move = minimaxAI.chooseMove(getBoardCopy());
            default -> move = minimaxAI.chooseMove(getBoardCopy());
        }
        if (move == null) {
            return false;
        }
        return makeMove(move.getRow(), move.getColumn());
    }

    public void resetGame() {
        board.reset();
        gameState.reset();
    }

    public Player getCurrentPlayer() {
        return gameState.getCurrentPlayer();
    }

    public Player getWinner() {
        return gameState.getWinner();
    }

    public boolean isGameOver() {
        return gameState.isGameOver();
    }

    public boolean isDraw() {
        return gameState.isGameOver()
                && gameState.getWinner() == Player.EMPTY;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Player getCell(int row, int col) {
        return board.getCell(row, col);
    }

    public Board getBoardCopy() {
        return new Board(board);
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
    public GameMode getGameMode() {
        return gameMode;
    }
    public boolean isHumanVsComputer() {
        return gameMode == GameMode.HUMAN_VS_COMPUTER;
    }

    public int getBoardSize() {
        return board.getSize();
    }

    public int getXWins() {
        return scoreBoard.getXWins();
    }

    public int getOWins() {
        return scoreBoard.getOWins();
    }

    public int getDraws() {
        return scoreBoard.getDraws();
    }

    public List<Move> getWinningLine() {
        return board.getWinningLine();
    }

    public void resetScores() {
        scoreBoard.reset();
    }
}