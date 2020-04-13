import org.junit.Test;
import sudoku.Move;
import sudoku.Sudoku;

import java.util.List;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Morten on 11/01/2017.
 */
public class SudokuTest {

    @Test
    public void testSimple() {
        int[][] start = {
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}};

        int[][] solution = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
        };


        Sudoku sudoku = new Sudoku(start, 9);
        Queue<Move> moves = sudoku.getMovesToSolve();
        while (!moves.isEmpty()) {
            Move nextMove = moves.poll();
            sudoku.makeMove(nextMove);
        }
        sudoku.printBoard(sudoku.getBoard());


        assertThat("Board should be solved!", solution, is(sudoku.getBoard()));

    }

//    @Test
//    public void testHard() {
//        int[][] board = {
//                {8,0,0,0,0,0,0,0,0},
//                {0,0,3,6,0,0,0,0,0},
//                {0,7,0,0,9,0,2,0,0},
//                {0,5,0,0,0,7,0,0,0},
//                {0,0,0,0,4,5,7,0,0},
//                {0,0,0,1,0,0,0,3,0},
//                {0,0,1,0,0,0,0,6,8},
//                {0,0,8,5,0,0,0,1,0},
//                {0,9,0,0,0,0,4,0,0}
//        };
//
//        Sudoku sudoku = new Sudoku(board, 9);
//        Queue<Move> moves = sudoku.getMovesToSolve();
//        while (!moves.isEmpty()) {
//            Move nextMove = moves.poll();
//            sudoku.makeMove(nextMove);
//        }
//        sudoku.printBoard(sudoku.getBoard());
//    }

    @Test
    public void testFourByFour() {
        int[][] board = {
                {0,0,10,1,0,0,15,0,0,0,0,4,7,0,0,0},
                {9,12,0,4,0,0,0,0,8,0,0,0,5,0,0,0},
                {0,3,0,6,14,12,11,0,1,0,0,10,16,0,4,0},
                {0,13,0,0,0,0,0,0,3,7,0,0,0,0,0,0},
                {0,0,0,9,5,0,0,0,0,0,3,16,0,13,0,0},
                {0,0,5,0,16,0,0,14,0,0,9,0,11,0,2,0},
                {0,10,0,15,0,0,6,8,0,4,0,1,0,0,7,16},
                {0,4,0,0,14,3,0,0,7,0,0,15,6,0,0,8,0},
                {7,0,6,3,0,13,16,0,0,0,8,0,10,0,0,0},
                {16,1,0,8,10,0,9,0,13,11,4,0,0,0,0,0},
                {0,4,0,0,0,14,0,0,15,0,0,0,0,0,9,0},
                {5,0,0,0,0,0,4,1,0,16,0,0,0,0,0,0},
                {10,0,0,0,0,0,3,0,0,0,0,0,0,15,0,12},
                {0,0,2,0,0,0,5,4,0,0,14,15,0,0,0,0},
                {8,7,0,16,1,0,0,15,0,6,13,0,3,5,0,0},
                {0,0,4,0,0,7,0,13,0,1,0,0,0,2,11,14}
        };

        Sudoku sudoku = new Sudoku(board, 16);
        Queue<Move> moves = sudoku.getMovesToSolve();
        while (!moves.isEmpty()) {
            Move nextMove = moves.poll();
            sudoku.makeMove(nextMove);
        }
        sudoku.printBoard(sudoku.getBoard());
    }
}
