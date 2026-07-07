package com.janish.tictactoe.ai;

import com.janish.tictactoe.model.Board;
import com.janish.tictactoe.model.Move;
import com.janish.tictactoe.model.Player;

public class MinimaxAI {

    private final Player aiPlayer;
    private final Player humanPlayer;

    public MinimaxAI(Player aiPlayer) {
        this.aiPlayer = aiPlayer;
        this.humanPlayer = aiPlayer.opposite();
    }

    public Move chooseMove(Board board) {

        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (Move move : board.getAvailableMoves()) {

            board.makeMove(move, aiPlayer);

            int score = minimax(board, false,1);

            board.clearCell(move);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
                bestMove.setScore(score);
            }
        }

        return bestMove;
    }

    private int minimax(Board board, boolean maximizingPlayer, int depth) {

        if (board.hasWinner() || board.isFull()) {
            return evaluate(board, depth);
        }

        if (maximizingPlayer) {

            int bestScore = Integer.MIN_VALUE;

            for (Move move : board.getAvailableMoves()) {

                board.makeMove(move, aiPlayer);

                int score = minimax(board, false, depth + 1);

                board.clearCell(move);

                bestScore = Math.max(bestScore, score);
            }

            return bestScore;

        } else {

            int bestScore = Integer.MAX_VALUE;

            for (Move move : board.getAvailableMoves()) {

                board.makeMove(move, humanPlayer);

                int score = minimax(board, true, depth + 1);

                board.clearCell(move);

                bestScore = Math.min(bestScore, score);
            }

            return bestScore;
        }
    }

    private int evaluate(Board board, int depth) {

        Player winner = board.getWinner();
        if (winner == aiPlayer) {
            return 10 - depth;
        }
        if (winner == humanPlayer) {
            return depth - 10;
        }
        return 0;
    }
}    