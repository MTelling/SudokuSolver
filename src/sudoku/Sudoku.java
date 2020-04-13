package sudoku;

import java.util.*;

/**
 * Created by Morten on 11/01/2017.
 */
public class Sudoku {

    private int[][] board;
    private int size;
    private int squareSize;

    /**
     * Board should be divisible with 3.
     * @param board
     */
    public Sudoku(int[][] board, int size) {


        this.squareSize = (int)Math.sqrt(size);
        if (this.squareSize * this.squareSize != size) throw new IllegalArgumentException();
        this.board = board;
        this.size = size;


    }

    public void makeMove(Move move) {
        this.board[move.y][move.x] = move.newValue;
    }


    public Queue<Move> getMovesToSolve() {

        int[][] tempBoard = getCopyOfBoard();
        Queue<Move> moves = new LinkedList<>();
        Stack<Coordinate> coordinates = new Stack<>();
        int x = 0;
        int y = 0;
        boolean isBacktracking = false;
        int count = 0;

        while(x != size && y != size) {

            count++;
            if (count % 10000000 == 0) {
                System.out.println("Now tried " + count + " moves!");
                printBoard(board);
            }

            if (board[y][x] == 0 || isBacktracking) {

                boolean isPossible = false;
                for (int i = board[y][x] + 1; i <= size; i++) {
                    if (isValidInCross(x, y, i) && isValidInSquare(x, y, i)) {
                        board[y][x] = i;
                        moves.add(new Move(x, y, i));
                        coordinates.add(new Coordinate(x, y));
                        isPossible = true;
                        isBacktracking = false;
                        break;
                    }
                }

                if (!isPossible) {
                    board[y][x] = 0;
                    moves.add(new Move(x, y, 0));
                    isBacktracking = true;
                    Coordinate old = coordinates.pop();
                    x = old.x;
                    y = old.y;
                }


            }

            if (!isBacktracking) {
                x++;
                if (x == size) {
                    y++;
                    x = 0;
                }
            }

        }
        printBoard(this.board);
        this.board = tempBoard;

        return moves;

    }


    public void printBoard(int[][] board) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.printf("%d\t", board[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }




    private boolean isValidInCross(int x, int y, int testNumber) {

        for (int i = 0; i < board.length; i++) {
            if (board[y][i] == testNumber || board[i][x] == testNumber) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidInSquare(int x, int y, int testNumber) {

        int startX = (x / squareSize) * squareSize;
        int startY = (y / squareSize) * squareSize;

        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                int testX = startX + i;
                int testY = startY + j;
                if (board[testY][testX] == testNumber) {
                    return false;
                }
            }
        }


        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    private int[][] getCopyOfBoard() {
        int[][] copyOfBoard = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                copyOfBoard[i][j] = board[i][j];
            }
        }
        return copyOfBoard;
    }
}


class Coordinate {
    int x;
    int y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


