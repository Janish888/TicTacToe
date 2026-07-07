package com.janish.tictactoe.ai;

import java.util.List;
import java.util.Random;

import com.janish.tictactoe.model.Board;
import com.janish.tictactoe.model.Move;

public class RandomAI {

    private final Random random = new Random();

    public Move chooseMove(Board board) {
        List<Move> moves = board.getAvailableMoves();
        if (moves.isEmpty()) {
            return null;
        }
        return moves.get(random.nextInt(moves.size()));
    }
}