package sudoku;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Queue;

public class Main extends Application {

    private static final int duration = 2;
    private boolean isAnimating = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
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

        int[][] board = {
                {8,0,0,0,0,0,0,0,0},
                {0,0,3,6,0,0,0,0,0},
                {0,7,0,0,9,0,2,0,0},
                {0,5,0,0,0,7,0,0,0},
                {0,0,0,0,4,5,7,0,0},
                {0,0,0,1,0,0,0,3,0},
                {0,0,1,0,0,0,0,6,8},
                {0,0,8,5,0,0,0,1,0},
                {0,9,0,0,0,0,4,0,0}
        };

        int[][] board2 = {
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,3,0,8,5},
                {0,0,1,0,2,0,0,0,0},
                {0,0,0,0,0,7,0,0,0},
                {0,0,4,0,0,0,1,0,0},
                {0,9,0,0,0,0,0,0,0},
                {5,0,0,0,0,0,0,7,3},
                {0,0,2,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,9}
        };

        int[][] henney = {
                {0,0,5,0,9,0,0,4,0},
                {1,0,0,2,0,3,0,0,0},
                {0,0,0,0,8,5,0,1,0},
                {0,2,9,0,0,0,0,0,8},
                {3,0,0,0,0,0,0,0,0},
                {0,0,0,1,4,2,0,0,0},
                {0,0,0,0,0,0,0,9,0},
                {8,0,0,3,6,0,4,0,0},
                {7,0,6,0,0,0,0,0,0}
        };

        Sudoku sudoku = new Sudoku(henney, 9);


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();

        NumberTile[][] numberTiles = new NumberTile[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                NumberTile.State state = (sudoku.getBoard()[y][x] == 0)
                        ? NumberTile.State.UNTOUCHED : NumberTile.State.FINAL;

                numberTiles[y][x] = new NumberTile(sudoku.getBoard()[y][x], state);
                ((GridPane)root).add(numberTiles[y][x], x, y);
            }
        }


        if (!isAnimating) {
            isAnimating = true;

            Queue<Move> moves = sudoku.getMovesToSolve();
            System.out.println("I'm now going to show you " + moves.size() + " moves!");
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(duration),
                    ae -> {
                        if (!moves.isEmpty()) {
                            Move move = moves.poll();
                            sudoku.makeMove(move);
                            numberTiles[move.y][move.x].setText("" + sudoku.getBoard()[move.y][move.x]);
                        } else {
                            System.out.println("Finished animating!!");
                        }
                    }
            ));
            timeline.setCycleCount(moves.size());
            timeline.setOnFinished((ae) -> {
                for (int y = 0; y < 9; y++) {
                    for (int x = 0; x < 9; x++) {
                        numberTiles[y][x].setState(NumberTile.State.DONE);
                        numberTiles[y][x].update();
                    }
                }
            });
            timeline.play();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
